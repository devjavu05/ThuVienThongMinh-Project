package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.dto.response.projectResponse.notificationResponse.ThongBaoResponse;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.ChiTietPhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuDatTruoc;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuPhat;
import com.devjavu.standardProject.entity.projectEntity.notification.ThongBao;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.ChiTietPhieuMuonRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuDatTruocRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuPhatRepository;
import com.devjavu.standardProject.repository.projectRepo.notificationRepo.ThongBaoRepository;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.DocGiaRepository;
import com.devjavu.standardProject.service.standardService.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongBaoService {
    static final Set<String> RESERVATION_READY_STATUSES = Set.of("DA_CO_SACH", "CHO_GIAO");

    ThongBaoRepository thongBaoRepository;
    DocGiaRepository docGiaRepository;
    UserService userService;
    ChiTietPhieuMuonRepository chiTietPhieuMuonRepository;
    PhieuPhatRepository phieuPhatRepository;
    PhieuDatTruocRepository phieuDatTruocRepository;

    public List<ThongBaoResponse> getMyNotifications() {
        DocGia docGia = getCurrentDocGia();
        syncSystemNotifications(docGia);
        return thongBaoRepository.findAllByDocGiaOrderByCreatedAtDesc(docGia).stream()
                .map(this::toResponse)
                .toList();
    }

    public ThongBaoResponse getNotificationDetail(String id) {
        DocGia docGia = getCurrentDocGia();
        syncSystemNotifications(docGia);
        ThongBao thongBao = thongBaoRepository.findByIdAndDocGia(id, docGia)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_THONG_BAO));
        if (!thongBao.isRead()) {
            thongBao.setRead(true);
            thongBao = thongBaoRepository.save(thongBao);
        }
        return toResponse(thongBao);
    }

    public void markAllAsRead() {
        DocGia docGia = getCurrentDocGia();
        syncSystemNotifications(docGia);
        List<ThongBao> unreadNotifications = thongBaoRepository.findAllByDocGiaAndReadFalse(docGia);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        thongBaoRepository.saveAll(unreadNotifications);
    }

    public long getUnreadCount() {
        DocGia docGia = getCurrentDocGia();
        syncSystemNotifications(docGia);
        return thongBaoRepository.countByDocGiaAndReadFalse(docGia);
    }

    private void syncSystemNotifications(DocGia docGia) {
        LocalDate today = LocalDate.now();

        List<ChiTietPhieuMuon> borrowDetails = chiTietPhieuMuonRepository.findAllByPhieuMuon_NguoiMuonOrderByPhieuMuon_BorrowDateDescIdDesc(docGia);
        for (ChiTietPhieuMuon detail : borrowDetails) {
            if (isReturned(detail) || detail.getPhieuMuon() == null || detail.getPhieuMuon().getDueDate() == null) {
                continue;
            }
            LocalDate dueDate = detail.getPhieuMuon().getDueDate();
            String bookTitle = extractBookTitle(detail);
            if (dueDate.isBefore(today)) {
                createIfMissing(
                        docGia,
                        "OVERDUE:" + detail.getId(),
                        "Quá hạn trả sách",
                        "Cuốn \"" + bookTitle + "\" đã quá hạn trả từ ngày " + dueDate + ". Vui lòng hoàn trả sớm để tránh phát sinh thêm phí.",
                        "OVERDUE",
                        dueDate.atStartOfDay()
                );
            } else if (!dueDate.isAfter(today.plusDays(2))) {
                createIfMissing(
                        docGia,
                        "DUE_SOON:" + detail.getId() + ":" + dueDate,
                        "Sắp đến hạn trả sách",
                        "Cuốn \"" + bookTitle + "\" sẽ đến hạn vào ngày " + dueDate + ". Bạn nên sắp xếp thời gian hoàn trả đúng hạn.",
                        "REMINDER",
                        dueDate.atStartOfDay()
                );
            }
        }

        List<PhieuPhat> fines = phieuPhatRepository.findAllByPhieuMuon_NguoiMuonOrderByIdDesc(docGia);
        for (PhieuPhat fine : fines) {
            if (fine.isPaid()) continue;
            String bookTitle = fine.getCuonSach() != null && fine.getCuonSach().getDauSach() != null
                    ? fine.getCuonSach().getDauSach().getTitle()
                    : "tài liệu";
            createIfMissing(
                    docGia,
                    "FINE:" + fine.getId(),
                    "Bạn có khoản phạt chưa thanh toán",
                    "Khoản phạt cho \"" + bookTitle + "\" hiện chưa được thanh toán. Số tiền cần xử lý là " + fine.getAmount() + ".",
                    "FINE",
                    LocalDateTime.now()
            );
        }

        List<PhieuDatTruoc> reservations = phieuDatTruocRepository.findAllByDocGiaOrderByReservationDateDesc(docGia);
        for (PhieuDatTruoc reservation : reservations) {
            if (!StringUtils.hasText(reservation.getStatus()) || !RESERVATION_READY_STATUSES.contains(reservation.getStatus())) {
                continue;
            }
            String bookTitle = reservation.getDauSach() != null ? reservation.getDauSach().getTitle() : "đầu sách";
            createIfMissing(
                    docGia,
                    "RESERVATION_READY:" + reservation.getId(),
                    "Sách đặt trước đã sẵn sàng",
                    "Đầu sách \"" + bookTitle + "\" hiện đã có sẵn để nhận tại thư viện. Vui lòng đến quầy trong thời gian giữ chỗ.",
                    "RESERVATION_READY",
                    reservation.getReservationDate() != null ? reservation.getReservationDate().atStartOfDay() : LocalDateTime.now()
            );
        }
    }

    private void createIfMissing(DocGia docGia, String sourceKey, String title, String content, String type, LocalDateTime createdAt) {
        if (thongBaoRepository.existsByDocGiaAndSourceKey(docGia, sourceKey)) {
            return;
        }
        thongBaoRepository.save(ThongBao.builder()
                .docGia(docGia)
                .sourceKey(sourceKey)
                .title(title)
                .content(content)
                .type(type)
                .read(false)
                .createdAt(createdAt)
                .build());
    }

    private String extractBookTitle(ChiTietPhieuMuon detail) {
        if (detail.getCuonSach() != null && detail.getCuonSach().getDauSach() != null) {
            return detail.getCuonSach().getDauSach().getTitle();
        }
        return "đầu sách";
    }

    private boolean isReturned(ChiTietPhieuMuon detail) {
        if (detail.getReturnDate() != null) return true;
        return StringUtils.hasText(detail.getStatus())
                && ("RETURNED".equalsIgnoreCase(detail.getStatus()) || "DA_TRA".equalsIgnoreCase(detail.getStatus()));
    }

    private DocGia getCurrentDocGia() {
        User user = userService.getMyInfo();
        DocGia docGia = docGiaRepository.findByUser(user);
        if (docGia == null) {
            throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        }
        return docGia;
    }

    private ThongBaoResponse toResponse(ThongBao thongBao) {
        return ThongBaoResponse.builder()
                .id(thongBao.getId())
                .title(thongBao.getTitle())
                .content(thongBao.getContent())
                .type(thongBao.getType())
                .read(thongBao.isRead())
                .createdAt(thongBao.getCreatedAt())
                .build();
    }
}

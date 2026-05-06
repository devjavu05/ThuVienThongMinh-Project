package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.CuonSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachUpdateRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.QuanLyKhoCreateRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.ThemBanSaoRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.ChiTietPhieuMuonRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.DanhGiaRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuDatTruocRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuMuaRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuMuonRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuPhatRequest;
import com.devjavu.standardProject.configuration.BookCategoryCatalog;
import com.devjavu.standardProject.configuration.ShelfLocationCatalog;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.ChiTietTaiLieuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachLookupResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.DauSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuThuThuItemResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuThuThuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuTaiLieuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.ChiTietPhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.DanhGiaResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.LichSuMuonTraItemResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.LichSuMuonTraResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuDatTruocResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.AdminRevenueResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuaResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuPhatResponse;
import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import com.devjavu.standardProject.entity.projectEntity.bookManager.EBook;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.ChiTietPhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.DanhGia;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuDatTruoc;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMua;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuPhat;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import com.devjavu.standardProject.mapper.projectMapper.bookManagerMapper.CuonSachMapper;
import com.devjavu.standardProject.mapper.projectMapper.bookManagerMapper.DauSachMapper;
import com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper.ChiTietPhieuMuonMapper;
import com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper.PhieuDatTruocMapper;
import com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper.PhieuMuonMapper;
import com.devjavu.standardProject.mapper.projectMapper.buisinessTransactionsMapper.PhieuPhatMapper;
import com.devjavu.standardProject.repository.projectRepo.bookManagerRepo.CuonSachRepository;
import com.devjavu.standardProject.repository.projectRepo.bookManagerRepo.DauSachRepository;
import com.devjavu.standardProject.repository.projectRepo.bookManagerRepo.EBookRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.ChiTietPhieuMuonRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.DanhGiaRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuDatTruocRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuMuaRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuMuonRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuPhatRepository;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.DocGiaRepository;
import com.devjavu.standardProject.repository.projectRepo.userProfileRepo.NhanVienRepository;
import com.devjavu.standardProject.service.standardService.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookService {
    static final Set<String> ACTIVE_RESERVATION_STATUSES = Set.of("DANG_CHO", "DA_CO_SACH", "CHO_GIAO");
    static final int MAX_ACTIVE_RESERVATIONS = 2;
    static final int MAX_EBOOK_PURCHASES_PER_DAY = 5;
    static final int MAX_RENEWAL_COUNT = 2;
    static final int RENEWAL_DAYS = 7;
    static final Set<String> BANNED_WORDS = Set.of("dm", "địt", "dit", "fuck", "ngu");

    DauSachRepository dauSachRepository;
    DauSachMapper dauSachMapper;
    CuonSachMapper cuonSachMapper;
    CuonSachRepository cuonSachRepository;
    EBookRepository eBookRepository;
    PhieuMuonRepository phieuMuonRepository;
    ChiTietPhieuMuonRepository chiTietPhieuMuonRepository;
    PhieuDatTruocRepository phieuDatTruocRepository;
    PhieuMuaRepository phieuMuaRepository;
    PhieuPhatRepository phieuPhatRepository;
    DanhGiaRepository danhGiaRepository;
    PhieuMuonMapper phieuMuonMapper;
    ChiTietPhieuMuonMapper chiTietPhieuMuonMapper;
    PhieuDatTruocMapper phieuDatTruocMapper;
    PhieuPhatMapper phieuPhatMapper;
    UserService userService;
    NhanVienRepository nhanVienRepository;
    DocGiaRepository docGiaRepository;
    FileStorageService fileStorageService;

    public List<DauSachResponse> getDauSach() {
        return dauSachRepository.findAll().stream().map(dauSachMapper::toDauSachResponse).toList();
    }

    public String uploadBookCover(MultipartFile file) {
        return fileStorageService.storeBookCover(file);
    }

    public List<TraCuuTaiLieuResponse> traCuuTaiLieu(String keyword, String author, String category, Integer publishYear) {
        return dauSachRepository.findAll().stream()
                .filter(dauSach -> matchesKeyword(dauSach, keyword))
                .filter(dauSach -> matchesField(dauSach.getAuthor(), author))
                .filter(dauSach -> matchesField(dauSach.getCategory(), category))
                .filter(dauSach -> publishYear == null || publishYear.equals(dauSach.getPublishYear()))
                .sorted(Comparator
                        .comparing((DauSach dauSach) -> dauSach.getAverageRating() == null ? 0.0 : dauSach.getAverageRating()).reversed()
                        .thenComparing((DauSach dauSach) -> dauSach.getCreatedAt() == null ? LocalDateTime.MIN : dauSach.getCreatedAt(), Comparator.reverseOrder()))
                .map(this::toTraCuuTaiLieuResponse)
                .toList();
    }

    public TraCuuThuThuResponse traCuuThuThu(String keyword, String category, int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.max(size, 1);
        List<TraCuuThuThuItemResponse> filtered = dauSachRepository.findAll().stream()
                .filter(dauSach -> matchesThuThuKeyword(dauSach, keyword))
                .filter(dauSach -> matchesCategory(dauSach.getCategory(), category))
                .sorted(Comparator.comparing(
                        (DauSach dauSach) -> dauSach.getCreatedAt() == null ? LocalDateTime.MIN : dauSach.getCreatedAt(),
                        Comparator.reverseOrder()
                ))
                .map(this::toThuThuLookupItem)
                .toList();

        int fromIndex = Math.min(safePage * safeSize, filtered.size());
        int toIndex = Math.min(fromIndex + safeSize, filtered.size());
        int totalPages = filtered.isEmpty() ? 0 : (int) Math.ceil((double) filtered.size() / safeSize);

        return TraCuuThuThuResponse.builder()
                .items(filtered.subList(fromIndex, toIndex))
                .page(safePage)
                .size(safeSize)
                .totalItems(filtered.size())
                .totalPages(totalPages)
                .build();
    }

    public CuonSachLookupResponse traCuuCuonSachTheoBarcode(String barcode) {
        CuonSach cuonSach = findCuonSachById(barcode);
        String defaultLocation = cuonSach.getDauSach() != null
                ? ShelfLocationCatalog.buildBookLocation(cuonSach.getDauSach().getFloorNumber(), cuonSach.getDauSach().getCategory())
                : null;
        Optional<ChiTietPhieuMuon> activeBorrow = chiTietPhieuMuonRepository.findFirstByCuonSach_BarcodeAndReturnDateIsNullOrderByIdDesc(barcode);

        return CuonSachLookupResponse.builder()
                .bookId(cuonSach.getDauSach() != null ? cuonSach.getDauSach().getId() : null)
                .barcode(cuonSach.getBarcode())
                .title(cuonSach.getDauSach() != null ? cuonSach.getDauSach().getTitle() : null)
                .author(cuonSach.getDauSach() != null ? cuonSach.getDauSach().getAuthor() : null)
                .category(cuonSach.getDauSach() != null ? cuonSach.getDauSach().getCategory() : null)
                .defaultLocation(defaultLocation)
                .location(defaultLocation)
                .status(cuonSach.getStatus())
                .physicalCondition(StringUtils.hasText(cuonSach.getPhysicalCondition()) ? cuonSach.getPhysicalCondition() : "NEW")
                .available(cuonSach.isAvailable())
                .borrowerFullName(activeBorrow.map(ChiTietPhieuMuon::getPhieuMuon).map(PhieuMuon::getNguoiMuon).map(DocGia::getFullName).orElse(null))
                .borrowerEmail(activeBorrow.map(ChiTietPhieuMuon::getPhieuMuon).map(PhieuMuon::getNguoiMuon).map(DocGia::getEmail).orElse(null))
                .borrowDate(activeBorrow.map(ChiTietPhieuMuon::getPhieuMuon).map(PhieuMuon::getBorrowDate).orElse(null))
                .dueDate(activeBorrow.map(this::getEffectiveDueDate).orElse(null))
                .build();
    }

    public ChiTietTaiLieuResponse getChiTietTaiLieu(String id) {
        DauSach dauSach = findDauSachById(id);
        List<CuonSach> cuonSaches = cuonSachRepository.findAllByDauSach(dauSach);
        int availableCount = countAvailableCopies(cuonSaches);
        String defaultLocation = safeBuildBookLocation(dauSach.getFloorNumber(), dauSach.getCategory());
        List<String> viTriKe = StringUtils.hasText(defaultLocation) ? List.of(defaultLocation) : List.of();
        EBook eBook = findEBookSafely(id);
        DocGia docGia = getCurrentDocGiaOrNull();
        boolean owned = false;
        boolean canReview = false;

        if (docGia != null && eBook != null) {
            try {
                owned = phieuMuaRepository.existsByDocGiaAndEBook(docGia, eBook);
            } catch (Exception ignored) {
                owned = false;
            }
        }

        if (docGia != null) {
            try {
                canReview = hasReviewPermission(docGia, dauSach);
            } catch (Exception ignored) {
                canReview = false;
            }
        }

        return ChiTietTaiLieuResponse.builder()
                .id(dauSach.getId())
                .title(dauSach.getTitle())
                .author(dauSach.getAuthor())
                .category(dauSach.getCategory())
                .description(dauSach.getDescription())
                .longIntroduction(dauSach.getLongIntroduction())
                .floorNumber(dauSach.getFloorNumber())
                .shelfCode(dauSach.getShelfCode())
                .defaultLocation(defaultLocation)
                .publishYear(dauSach.getPublishYear())
                .coverImageUrl(dauSach.getCoverImageUrl())
                .averageRating(dauSach.getAverageRating())
                .totalQuantity(dauSach.getQuantity())
                .availableCount(availableCount)
                .tinhTrang(buildAvailabilityText(availableCount, eBook))
                .viTriKe(viTriKe)
                .hasEBook(eBook != null && StringUtils.hasText(eBook.getAccessLink()))
                .eBookLink(null)
                .eBookFormat(eBook != null ? eBook.getFormat() : null)
                .eBookFileSize(eBook != null ? eBook.getFileSize() : null)
                .eBookPrice(eBook != null ? eBook.getPrice() : null)
                .eBookPremiumOnly(eBook != null && eBook.isPremiumOnly())
                .eBookDownloadable(eBook != null && eBook.isDownloadable())
                .eBookUnderMaintenance(eBook != null && eBook.isUnderMaintenance())
                .eBookOwned(owned)
                .ownedAccessLink(owned && eBook != null && eBook.isDownloadable() ? eBook.getAccessLink() : null)
                .canReview(canReview)
                .build();
    }

    public List<DanhGiaResponse> getDanhGiaByBook(String bookId) {
        DauSach dauSach = findDauSachById(bookId);
        return danhGiaRepository.findAllByDauSachOrderByUpdatedAtDesc(dauSach).stream()
                .map(this::toDanhGiaResponse)
                .toList();
    }

    public DanhGiaResponse submitDanhGia(String bookId, DanhGiaRequest request) {
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new AppException(ErrorCode.REVIEW_INVALID_RATING);
        }
        if (containsBannedWords(request.getComment())) {
            throw new AppException(ErrorCode.REVIEW_INVALID_CONTENT);
        }

        DocGia docGia = getCurrentDocGia();
        DauSach dauSach = findDauSachById(bookId);

        if (!hasReviewPermission(docGia, dauSach)) {
            throw new AppException(ErrorCode.REVIEW_NOT_ALLOWED);
        }

        DanhGia danhGia = danhGiaRepository.findByDocGiaAndDauSach(docGia, dauSach)
                .orElse(DanhGia.builder().docGia(docGia).dauSach(dauSach).build());
        danhGia.setRating(request.getRating());
        danhGia.setComment(request.getComment());
        danhGia.setUpdatedAt(LocalDateTime.now());
        DanhGia saved = danhGiaRepository.save(danhGia);

        recalculateAverageRating(dauSach);
        return toDanhGiaResponse(saved);
    }

    public PhieuMuaResponse muaEBook(PhieuMuaRequest request) {
        DocGia docGia = getCurrentDocGia();
        User user = userService.getMyInfo();
        validateReaderEligible(docGia, user);

        EBook eBook = eBookRepository.findById(request.getEbookId())
                .orElseThrow(() -> new AppException(ErrorCode.EBOOK_NOT_FOUND));

        if (eBook.isUnderMaintenance()) throw new AppException(ErrorCode.EBOOK_UNDER_MAINTENANCE);
        if (phieuMuaRepository.existsByDocGiaAndEBook(docGia, eBook)) throw new AppException(ErrorCode.EBOOK_ALREADY_PURCHASED);

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        long todayPurchases = phieuMuaRepository.countByDocGiaAndPurchaseTimeBetween(docGia, startOfDay, endOfDay);
        if (todayPurchases >= MAX_EBOOK_PURCHASES_PER_DAY) throw new AppException(ErrorCode.EBOOK_DAILY_LIMIT);
        if (eBook.isPremiumOnly() && !"PREMIUM".equalsIgnoreCase(docGia.getCardType())) throw new AppException(ErrorCode.EBOOK_PREMIUM_REQUIRED);
        if (docGia.getBalance() < eBook.getPrice()) throw new AppException(ErrorCode.EBOOK_INSUFFICIENT_BALANCE);

        docGia.setBalance(docGia.getBalance() - eBook.getPrice());
        docGiaRepository.save(docGia);
        String unlockedAccessLink = eBook.isDownloadable() ? eBook.getAccessLink() : null;

        PhieuMua phieuMua = PhieuMua.builder()
                .docGia(docGia)
                .eBook(eBook)
                .purchaseTime(LocalDateTime.now())
                .amount(eBook.getPrice())
                .accessLink(unlockedAccessLink)
                .build();

        PhieuMua saved = phieuMuaRepository.save(phieuMua);
        return PhieuMuaResponse.builder()
                .id(saved.getId())
                .purchaseTime(saved.getPurchaseTime())
                .amount(saved.getAmount())
                .title(eBook.getDauSach().getTitle())
                .accessLink(saved.getAccessLink())
                .format(eBook.getFormat())
                .build();
    }

    public List<PhieuMuaResponse> getMyEBooks() {
        DocGia docGia = getCurrentDocGia();
        return phieuMuaRepository.findAllByDocGiaOrderByPurchaseTimeDesc(docGia).stream()
                .map(item -> PhieuMuaResponse.builder()
                        .id(item.getId())
                        .purchaseTime(item.getPurchaseTime())
                        .amount(item.getAmount())
                        .title(item.getEBook().getDauSach().getTitle())
                        .accessLink(item.getAccessLink())
                        .format(item.getEBook().getFormat())
                        .build())
                .toList();
    }

    public DauSachResponse createDauSach(DauSachCreationRequest request) {
        request.setCategory(normalizeCategory(request.getCategory()));
        DauSach dauSach = dauSachMapper.toDauSach(request);
        validateFloor(request.getFloorNumber());
        dauSach.setShelfCode(ShelfLocationCatalog.shelfCodeForCategory(request.getCategory()));
        dauSach.setQuantity(0);
        dauSach.setCreatedAt(LocalDateTime.now());
        dauSach.setAverageRating(request.getAverageRating() == null ? 0.0 : request.getAverageRating());
        DauSach savedBook = dauSachRepository.save(dauSach);
        syncEBook(savedBook, request.getAccessLink(), request.getEBookFormat(), request.getEBookFileSize(), request.getEBookPrice(),
                request.getEBookPremiumOnly(), request.getEBookDownloadable(), request.getEBookUnderMaintenance());
        return toDauSachResponseWithEBook(savedBook);
    }

    @Transactional
    public TraCuuThuThuItemResponse createInventoryBook(QuanLyKhoCreateRequest request) {
        validateInventoryCreateRequest(request);

        DauSach dauSach = DauSach.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .category(normalizeCategory(request.getCategory()))
                .description(request.getDescription())
                .longIntroduction(request.getLongIntroduction())
                .floorNumber(request.getFloorNumber())
                .shelfCode(ShelfLocationCatalog.shelfCodeForCategory(request.getCategory()))
                .publishYear(request.getPublishYear())
                .coverImageUrl(request.getCoverImageUrl())
                .averageRating(0.0)
                .createdAt(LocalDateTime.now())
                .quantity(0)
                .build();
        DauSach savedBook = dauSachRepository.save(dauSach);
        syncEBook(savedBook, request.getAccessLink(), request.getEBookFormat(), request.getEBookFileSize(), request.getEBookPrice(),
                request.getEBookPremiumOnly(), request.getEBookDownloadable(), request.getEBookUnderMaintenance());

        int copyCount = request.getCopyCount() == null ? 0 : request.getCopyCount();
        for (int index = 0; index < copyCount; index++) {
            CuonSach copy = CuonSach.builder()
                    .dauSach(savedBook)
                    .stt(index + 1)
                    .barcode(generateLocationBasedBarcode(savedBook.getCategory(), savedBook.getFloorNumber(), savedBook.getShelfCode()))
                    .location(null)
                    .status("AVAILABLE")
                    .physicalCondition(StringUtils.hasText(request.getPhysicalCondition()) ? request.getPhysicalCondition() : "NEW")
                    .isAvailable(true)
                    .build();
            cuonSachRepository.save(copy);
        }

        savedBook.setQuantity(copyCount);
        dauSachRepository.save(savedBook);
        return toThuThuLookupItem(savedBook);
    }

    public List<CuonSachResponse> getCuonSach(String id) {
        DauSach dauSach = findDauSachById(id);
        return cuonSachRepository.findAllByDauSach(dauSach).stream().map(cuonSachMapper::toCuonSachResponse).toList();
    }

    public CuonSachResponse createCuonSach(CuonSachCreationRequest request) {
        CuonSach cuonSach = cuonSachMapper.toCuonSach(request);
        DauSach dauSach = findDauSachById(request.getDauSach());
        dauSach.setQuantity(dauSach.getQuantity() + 1);
        cuonSach.setDauSach(dauSach);
        cuonSach.setBarcode(generateLocationBasedBarcode(dauSach.getCategory(), dauSach.getFloorNumber(), dauSach.getShelfCode()));
        cuonSach.setLocation(null);
        cuonSach.setAvailable(!"LOST".equalsIgnoreCase(request.getStatus()) && !"THANH_LY".equalsIgnoreCase(request.getStatus()));
        cuonSach.setStatus(StringUtils.hasText(request.getStatus()) ? request.getStatus() : "AVAILABLE");
        cuonSach.setPhysicalCondition(StringUtils.hasText(request.getPhysicalCondition()) ? request.getPhysicalCondition() : "NEW");
        cuonSach.setStt(dauSach.getQuantity());
        return cuonSachMapper.toCuonSachResponse(cuonSachRepository.save(cuonSach));
    }

    @Transactional
    public List<CuonSachResponse> createCopiesForExistingBook(String dauSachId, ThemBanSaoRequest request) {
        DauSach dauSach = findDauSachById(dauSachId);
        int copyCount = request.getCopyCount() == null ? 0 : request.getCopyCount();
        if (copyCount <= 0) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        int startingIndex = (int) cuonSachRepository.findAllByDauSach(dauSach).stream()
                .filter(copy -> !"THANH_LY".equalsIgnoreCase(copy.getStatus()))
                .count();

        List<CuonSachResponse> createdCopies = new java.util.ArrayList<>();
        for (int index = 0; index < copyCount; index++) {
            String normalizedStatus = StringUtils.hasText(request.getStatus()) ? request.getStatus() : "AVAILABLE";
            CuonSach copy = CuonSach.builder()
                    .dauSach(dauSach)
                    .stt(startingIndex + index + 1)
                    .barcode(generateLocationBasedBarcode(dauSach.getCategory(), dauSach.getFloorNumber(), dauSach.getShelfCode()))
                    .location(null)
                    .status(normalizedStatus)
                    .physicalCondition(StringUtils.hasText(request.getPhysicalCondition()) ? request.getPhysicalCondition() : "NEW")
                    .isAvailable("AVAILABLE".equalsIgnoreCase(normalizedStatus) || "SAN_SANG".equalsIgnoreCase(normalizedStatus))
                    .build();
            createdCopies.add(cuonSachMapper.toCuonSachResponse(cuonSachRepository.save(copy)));
        }

        long activeCopies = cuonSachRepository.findAllByDauSach(dauSach).stream()
                .filter(copy -> !"THANH_LY".equalsIgnoreCase(copy.getStatus()))
                .count();
        dauSach.setQuantity((int) activeCopies);
        dauSachRepository.save(dauSach);

        return createdCopies;
    }

    @Transactional
    public CuonSachResponse liquidateCopy(String barcode) {
        CuonSach cuonSach = findCuonSachById(barcode);
        if (!cuonSach.isAvailable() && !"LOST".equalsIgnoreCase(cuonSach.getStatus())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        cuonSach.setAvailable(false);
        cuonSach.setStatus("THANH_LY");
        cuonSach.setPhysicalCondition("DISPOSED");
        CuonSach savedCopy = cuonSachRepository.save(cuonSach);

        DauSach dauSach = savedCopy.getDauSach();
        long activeCopies = cuonSachRepository.findAllByDauSach(dauSach).stream()
                .filter(copy -> !"THANH_LY".equalsIgnoreCase(copy.getStatus()))
                .count();
        dauSach.setQuantity((int) activeCopies);
        dauSachRepository.save(dauSach);

        return cuonSachMapper.toCuonSachResponse(savedCopy);
    }

    @Transactional
    public DauSachResponse updateDauSach(DauSachUpdateRequest request, String id) {
        DauSach dauSach = findDauSachById(id);
        request.setCategory(normalizeCategory(request.getCategory()));
        validateFloor(request.getFloorNumber());
        dauSachMapper.updateDauSach(dauSach, request);
        dauSach.setShelfCode(ShelfLocationCatalog.shelfCodeForCategory(dauSach.getCategory()));
        if (dauSach.getCreatedAt() == null) dauSach.setCreatedAt(LocalDateTime.now());
        if (dauSach.getAverageRating() == null) dauSach.setAverageRating(0.0);
        DauSach savedBook = dauSachRepository.save(dauSach);
        syncEBook(savedBook, request.getAccessLink(), request.getEBookFormat(), request.getEBookFileSize(), request.getEBookPrice(),
                request.getEBookPremiumOnly(), request.getEBookDownloadable(), request.getEBookUnderMaintenance());
        return toDauSachResponseWithEBook(savedBook);
    }

    @Transactional
    public void deleteDauSach(String id) {
        DauSach dauSach = findDauSachById(id);
        List<CuonSach> copies = cuonSachRepository.findAllByDauSach(dauSach);
        List<PhieuPhat> finesByBook = phieuPhatRepository.findAllByCuonSach_DauSach(dauSach);

        if (!finesByBook.isEmpty()) {
            for (PhieuPhat fine : finesByBook) {
                if (fine.getPhieuMuon() != null && fine.getPhieuMuon().getNguoiMuon() != null) {
                    DocGia docGia = fine.getPhieuMuon().getNguoiMuon();
                    int nextTotalFines = Math.max(0, docGia.getTotalFines() - (int) fine.getAmount());
                    docGia.setTotalFines(nextTotalFines);
                    docGiaRepository.save(docGia);
                }
            }
            phieuPhatRepository.deleteAll(finesByBook);
            phieuPhatRepository.flush();
        }

        List<ChiTietPhieuMuon> relatedDetails = chiTietPhieuMuonRepository.findAllByCuonSach_DauSach(dauSach);
        Set<PhieuMuon> affectedTickets = new HashSet<>();
        for (ChiTietPhieuMuon detail : relatedDetails) {
            if (detail.getPhieuMuon() != null) {
                affectedTickets.add(detail.getPhieuMuon());
            }
        }

        if (!relatedDetails.isEmpty()) {
            chiTietPhieuMuonRepository.deleteAll(relatedDetails);
            chiTietPhieuMuonRepository.flush();
        }

        for (PhieuMuon phieuMuon : affectedTickets) {
            List<ChiTietPhieuMuon> remainingDetails = chiTietPhieuMuonRepository.findAllByPhieuMuonOrderByIdAsc(phieuMuon);
            if (remainingDetails.isEmpty()) {
                List<PhieuPhat> relatedFines = phieuPhatRepository.findAllByPhieuMuon(phieuMuon);
                if (!relatedFines.isEmpty()) {
                    for (PhieuPhat fine : relatedFines) {
                        if (phieuMuon.getNguoiMuon() != null) {
                            DocGia docGia = phieuMuon.getNguoiMuon();
                            int nextTotalFines = Math.max(0, docGia.getTotalFines() - (int) fine.getAmount());
                            docGia.setTotalFines(nextTotalFines);
                            docGiaRepository.save(docGia);
                        }
                    }
                    phieuPhatRepository.deleteAll(relatedFines);
                    phieuPhatRepository.flush();
                }
                phieuMuonRepository.delete(phieuMuon);
            }
        }
        phieuMuonRepository.flush();

        EBook eBook = eBookRepository.findById(dauSach.getId()).orElse(null);
        if (eBook != null) {
            List<PhieuMua> purchases = phieuMuaRepository.findAllByEBook(eBook);
            if (!purchases.isEmpty()) {
                phieuMuaRepository.deleteAll(purchases);
                phieuMuaRepository.flush();
            }
            eBookRepository.delete(eBook);
            eBookRepository.flush();
        }

        if (!copies.isEmpty()) {
            cuonSachRepository.deleteAll(copies);
            cuonSachRepository.flush();
        }

        List<PhieuDatTruoc> reservations = phieuDatTruocRepository.findAllByDauSach(dauSach);
        if (!reservations.isEmpty()) {
            phieuDatTruocRepository.deleteAll(reservations);
            phieuDatTruocRepository.flush();
        }

        List<DanhGia> reviews = danhGiaRepository.findAllByDauSach(dauSach);
        if (!reviews.isEmpty()) {
            danhGiaRepository.deleteAll(reviews);
            danhGiaRepository.flush();
        }

        dauSachRepository.delete(dauSach);
    }

    public DauSachResponse getDauSachById(String id) {
        return toDauSachResponseWithEBook(findDauSachById(id));
    }

    public PhieuMuonResponse createPhieuMuon(PhieuMuonRequest request) {
        PhieuMuon phieuMuon = phieuMuonMapper.toPhieuMuon(request);
        User user = userService.getMyInfo();
        NhanVien nhanVien = nhanVienRepository.findByUser(user);
        if (nhanVien == null) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        String normalizedEmail = request.getEmail() != null ? request.getEmail().trim() : null;
        DocGia docGia = StringUtils.hasText(normalizedEmail)
                ? docGiaRepository.findByEmailIgnoreCase(normalizedEmail)
                : null;
        if (docGia == null) throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        LocalDate today = LocalDate.now();
        if (request.getDueDate() == null || request.getDueDate().isBefore(today)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        phieuMuon.setBorrowDate(today);
        phieuMuon.setDueDate(request.getDueDate());
        phieuMuon.setEditable(true);
        phieuMuon.setNguoiMuon(docGia);
        phieuMuon.setNhanVien(nhanVien);
        return phieuMuonMapper.toPhieuMuonResponse(phieuMuonRepository.save(phieuMuon));
    }

    public List<PhieuMuonResponse> getPhieuMuon() {
        return phieuMuonRepository.findAll().stream()
                .filter(this::hasBorrowDetails)
                .map(phieuMuonMapper::toPhieuMuonResponse)
                .toList();
    }

    public PhieuMuonResponse getPhieuMuonById(String id) {
        return phieuMuonMapper.toPhieuMuonResponse(findPhieuMuonById(id));
    }

    @Transactional
    public void deletePhieuMuon(String id) {
        PhieuMuon phieuMuon = findPhieuMuonById(id);
        List<ChiTietPhieuMuon> details = chiTietPhieuMuonRepository.findAllByPhieuMuonOrderByIdAsc(phieuMuon);
        
        // Phiếu phải có ít nhất 1 chi tiết
        if (details.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        
        // Kiểm tra xem tất cả chi tiết đã trả chưa
        boolean allReturned = details.stream().allMatch(this::isReturned);
        if (!allReturned) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        
        // Xóa tất cả chi tiết phiếu trước
        List<PhieuPhat> relatedFines = phieuPhatRepository.findAllByPhieuMuon(phieuMuon);
        if (!relatedFines.isEmpty()) {
            DocGia docGia = phieuMuon.getNguoiMuon();
            if (docGia != null) {
                int deduction = relatedFines.stream()
                        .mapToInt(fine -> (int) fine.getAmount())
                        .sum();
                docGia.setTotalFines(Math.max(0, docGia.getTotalFines() - deduction));
                docGiaRepository.save(docGia);
            }
            phieuPhatRepository.deleteAll(relatedFines);
        }
        chiTietPhieuMuonRepository.deleteAll(details);
        
        // Sau đó xóa phiếu
        phieuMuonRepository.delete(phieuMuon);
    }

    public List<PhieuMuonResponse> searchPhieuMuon(String nguoiMuonName, LocalDate borrowDateFrom, LocalDate borrowDateTo) {
        List<PhieuMuon> allTickets = phieuMuonRepository.findAll();
        
        return allTickets.stream()
                .filter(this::hasBorrowDetails)
                .filter(ticket -> {
                    // Filter by người mượn name
                    if (nguoiMuonName != null && !nguoiMuonName.trim().isEmpty()) {
                        String searchTerm = nguoiMuonName.toLowerCase().trim();
                        String fullName = (ticket.getNguoiMuon() != null ? ticket.getNguoiMuon().getFullName() : "").toLowerCase();
                        String email = (ticket.getNguoiMuon() != null ? ticket.getNguoiMuon().getEmail() : "").toLowerCase();
                        if (!fullName.contains(searchTerm) && !email.contains(searchTerm)) {
                            return false;
                        }
                    }
                    
                    // Filter by borrow date range
                    if (borrowDateFrom != null && ticket.getBorrowDate() != null && ticket.getBorrowDate().isBefore(borrowDateFrom)) {
                        return false;
                    }
                    if (borrowDateTo != null && ticket.getBorrowDate() != null && ticket.getBorrowDate().isAfter(borrowDateTo)) {
                        return false;
                    }
                    
                    return true;
                })
                .sorted((t1, t2) -> {
                    // Sort by borrow date descending
                    if (t1.getBorrowDate() == null || t2.getBorrowDate() == null) return 0;
                    return t2.getBorrowDate().compareTo(t1.getBorrowDate());
                })
                .map(phieuMuonMapper::toPhieuMuonResponse)
                .toList();
    }

    public ChiTietPhieuMuonResponse createChiTietPhieuMuon(ChiTietPhieuMuonRequest request) {
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonMapper.toChiTietPhieuMuon(request);
        PhieuMuon phieuMuon = findPhieuMuonById(request.getPhieuMuonId());
        if (!phieuMuon.isEditable()) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        CuonSach cuonSach = findCuonSachById(request.getCuonSachBarcode());
        validateReservationPriorityForBorrow(phieuMuon, cuonSach);
        chiTietPhieuMuon.setPhieuMuon(phieuMuon);
        chiTietPhieuMuon.setCuonSach(cuonSach);
        chiTietPhieuMuon.setDueDate(phieuMuon.getDueDate());
        chiTietPhieuMuon.setRenewalCount(0);
        cuonSach.setAvailable(false);
        cuonSach.setStatus("DANG_MUON");
        cuonSachRepository.save(cuonSach);
        ChiTietPhieuMuon savedDetail = chiTietPhieuMuonRepository.save(chiTietPhieuMuon);
        markReservationFulfilledIfNeeded(phieuMuon, cuonSach);
        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(savedDetail);
    }

    public PhieuMuonResponse finalizePhieuMuon(String id) {
        PhieuMuon phieuMuon = findPhieuMuonById(id);
        phieuMuon.setEditable(false);
        return phieuMuonMapper.toPhieuMuonResponse(phieuMuonRepository.save(phieuMuon));
    }

    public List<ChiTietPhieuMuonResponse> getChiTietPhieuMuon() {
        return chiTietPhieuMuonRepository.findAll().stream().map(chiTietPhieuMuonMapper::toChiTietPhieuMuonResponse).toList();
    }

    public ChiTietPhieuMuonResponse getChiTietPhieuMuonById(Long id) {
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CHI_TIET_PHIEU_MUON));
        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(chiTietPhieuMuon);
    }

    public ChiTietPhieuMuonResponse renewChiTietPhieuMuon(Long id) {
        DocGia docGia = getCurrentDocGia();
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CHI_TIET_PHIEU_MUON));

        if (chiTietPhieuMuon.getPhieuMuon() == null
                || chiTietPhieuMuon.getPhieuMuon().getNguoiMuon() == null
                || !docGia.getId().equals(chiTietPhieuMuon.getPhieuMuon().getNguoiMuon().getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        renewPhieuMuon(chiTietPhieuMuon.getPhieuMuon().getId());
        ChiTietPhieuMuon refreshed = chiTietPhieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CHI_TIET_PHIEU_MUON));
        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(refreshed);
    }

    public PhieuMuonResponse renewPhieuMuon(String id) {
        DocGia docGia = getCurrentDocGia();
        PhieuMuon phieuMuon = findPhieuMuonById(id);
        if (phieuMuon.getNguoiMuon() == null || !docGia.getId().equals(phieuMuon.getNguoiMuon().getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        List<ChiTietPhieuMuon> details = chiTietPhieuMuonRepository.findAllByPhieuMuonOrderByIdAsc(phieuMuon);
        List<ChiTietPhieuMuon> activeDetails = details.stream()
                .filter(detail -> !isReturned(detail))
                .toList();

        if (activeDetails.isEmpty()) {
            throw new AppException(ErrorCode.RENEWAL_OVERDUE_NOT_ALLOWED);
        }

        validateRenewalRules(docGia, phieuMuon, activeDetails);

        LocalDate newDueDate = phieuMuon.getDueDate().plusDays(RENEWAL_DAYS);
        phieuMuon.setDueDate(newDueDate);
        phieuMuon.setRenewalCount(phieuMuon.getRenewalCount() + 1);
        activeDetails.forEach(detail -> {
            detail.setDueDate(newDueDate);
            detail.setRenewalCount(phieuMuon.getRenewalCount());
            chiTietPhieuMuonRepository.save(detail);
        });

        return phieuMuonMapper.toPhieuMuonResponse(phieuMuonRepository.save(phieuMuon));
    }

    public ChiTietPhieuMuonResponse returnChiTietPhieuMuon(Long id) {
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CHI_TIET_PHIEU_MUON));

        if (isReturned(chiTietPhieuMuon)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        chiTietPhieuMuon.setReturnDate(LocalDate.now());
        chiTietPhieuMuon.setStatus("DA_TRA");

        CuonSach cuonSach = chiTietPhieuMuon.getCuonSach();
        if (cuonSach != null) {
            cuonSach.setAvailable(true);
            cuonSach.setStatus("AVAILABLE");
            cuonSachRepository.save(cuonSach);

            if (cuonSach.getDauSach() != null) {
                phieuDatTruocRepository.findFirstByDauSachAndStatusInOrderByReservationDateAscIdAsc(
                        cuonSach.getDauSach(),
                        ACTIVE_RESERVATION_STATUSES
                ).ifPresent(firstReservation -> {
                    if ("DANG_CHO".equalsIgnoreCase(firstReservation.getStatus())) {
                        firstReservation.setStatus("DA_CO_SACH");
                        phieuDatTruocRepository.save(firstReservation);
                    }
                });
            }
        }

        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(chiTietPhieuMuonRepository.save(chiTietPhieuMuon));
    }

    public PhieuDatTruocResponse createPhieuDatTruoc(PhieuDatTruocRequest request) {
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocMapper.toPhieuDatTruoc(request);
        User user = userService.getMyInfo();
        DocGia docGia = docGiaRepository.findByUser(user);
        if (docGia == null) throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);

        validateReaderEligible(docGia, user);
        DauSach dauSach = findDauSachById(request.getDauSachId());
        validateReservationRules(docGia, dauSach);

        phieuDatTruoc.setDocGia(docGia);
        phieuDatTruoc.setDauSach(dauSach);
        phieuDatTruoc.setReservationDate(LocalDate.now());
        phieuDatTruoc.setStatus("DANG_CHO");
        return toReservationResponse(phieuDatTruocRepository.save(phieuDatTruoc));
    }

    public List<PhieuDatTruocResponse> getPhieuDatTruoc() {
        User user = userService.getMyInfo();
        boolean isStaff = user.getRoles() != null && user.getRoles().stream().anyMatch(role -> "NHAN_VIEN".equals(role.getName()) || "CHU_THU_VIEN".equals(role.getName()));
        List<PhieuDatTruoc> reservations = isStaff ? phieuDatTruocRepository.findAll() : phieuDatTruocRepository.findAllByDocGiaOrderByReservationDateDesc(docGiaRepository.findByUser(user));
        return reservations.stream().map(this::toReservationResponse).toList();
    }

    public PhieuDatTruocResponse getPhieuDatTruocById(String id) {
        return toReservationResponse(phieuDatTruocRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC)));
    }

    public List<PhieuDatTruocResponse> getReservationsByDauSach(String dauSachId) {
        DauSach dauSach = findDauSachById(dauSachId);
        List<PhieuDatTruoc> reservations = phieuDatTruocRepository.findAllByDauSachAndStatusInOrderByReservationDateAscIdAsc(dauSach, ACTIVE_RESERVATION_STATUSES);
        return reservations.stream().map(this::toReservationResponse).toList();
    }

    public PhieuDatTruocResponse confirmReservation(String id) {
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC));
        if (!"DA_CO_SACH".equalsIgnoreCase(phieuDatTruoc.getStatus())) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        phieuDatTruoc.setStatus("CHO_GIAO");
        phieuDatTruoc.setConfirmedDate(LocalDate.now());
        return toReservationResponse(phieuDatTruocRepository.save(phieuDatTruoc));
    }

    public LichSuMuonTraResponse getMyBorrowHistory() {
        DocGia docGia = getCurrentDocGia();
        List<ChiTietPhieuMuon> details = chiTietPhieuMuonRepository.findAllByPhieuMuon_NguoiMuonOrderByPhieuMuon_BorrowDateDescIdDesc(docGia);
        java.util.Map<String, List<ChiTietPhieuMuon>> groupedByTicket = details.stream()
                .filter(detail -> detail.getPhieuMuon() != null && detail.getPhieuMuon().getId() != null)
                .collect(java.util.stream.Collectors.groupingBy(detail -> detail.getPhieuMuon().getId(), java.util.LinkedHashMap::new, java.util.stream.Collectors.toList()));

        List<LichSuMuonTraItemResponse> dangMuon = groupedByTicket.values().stream()
                .filter(group -> group.stream().anyMatch(detail -> !isReturned(detail)))
                .map(this::toBorrowHistoryTicketItem)
                .toList();
        List<LichSuMuonTraItemResponse> daTra = groupedByTicket.values().stream()
                .filter(group -> group.stream().allMatch(this::isReturned))
                .map(this::toBorrowHistoryTicketItem)
                .toList();
        List<PhieuPhatResponse> phieuPhat = phieuPhatRepository.findAllByPhieuMuon_NguoiMuonOrderByIdDesc(docGia).stream()
                .map(phieuPhatMapper::toPhieuPhatResponse)
                .toList();

        return LichSuMuonTraResponse.builder()
                .dangMuon(dangMuon)
                .daTra(daTra)
                .phieuPhat(phieuPhat)
                .build();
    }

    public void cancelPhieuDatTruoc(String id) {
        User user = userService.getMyInfo();
        DocGia docGia = docGiaRepository.findByUser(user);
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC));
        if (docGia == null || phieuDatTruoc.getDocGia() == null || !phieuDatTruoc.getDocGia().getId().equals(docGia.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        if (!ACTIVE_RESERVATION_STATUSES.contains(phieuDatTruoc.getStatus())) {
            throw new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC);
        }
        phieuDatTruoc.setStatus("DA_HUY");
        phieuDatTruocRepository.save(phieuDatTruoc);
    }

    public void deletePhieuDatTruoc(String id) {
        User user = userService.getMyInfo();
        DocGia docGia = docGiaRepository.findByUser(user);
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC));

        if (docGia == null || phieuDatTruoc.getDocGia() == null || !phieuDatTruoc.getDocGia().getId().equals(docGia.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        if (ACTIVE_RESERVATION_STATUSES.contains(phieuDatTruoc.getStatus())) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        phieuDatTruocRepository.delete(phieuDatTruoc);
    }

    public PhieuPhatResponse createPhieuPhat(PhieuPhatRequest request) {
        PhieuPhat phieuPhat = phieuPhatMapper.toPhieuPhat(request);
        PhieuMuon phieuMuon = findPhieuMuonById(request.getPhieuMuonId());
        if (!hasBorrowDetails(phieuMuon)) {
            throw new AppException(ErrorCode.NOT_FOUND_PHIEU_MUON);
        }
        List<ChiTietPhieuMuon> details = chiTietPhieuMuonRepository.findAllByPhieuMuonOrderByIdAsc(phieuMuon);
        if (!details.isEmpty() && details.stream().allMatch(this::isReturned)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        phieuPhat.setPhieuMuon(phieuMuon);
        phieuPhat.setPaid(false);
        DocGia docGia = phieuMuon.getNguoiMuon();
        docGia.setTotalFines(docGia.getTotalFines() + (int) request.getAmount());
        docGiaRepository.save(docGia);
        return phieuPhatMapper.toPhieuPhatResponse(phieuPhatRepository.save(phieuPhat));
    }

    public List<PhieuPhatResponse> getPhieuPhat() {
        return phieuPhatRepository.findAll().stream().map(phieuPhatMapper::toPhieuPhatResponse).toList();
    }

    public PhieuPhatResponse getPhieuPhatById(String id) {
        return phieuPhatMapper.toPhieuPhatResponse(phieuPhatRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_PHAT)));
    }

    @Transactional
    public PhieuPhatResponse payPhieuPhat(String id) {
        DocGia docGia = getCurrentDocGia();
        PhieuPhat phieuPhat = phieuPhatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_PHAT));

        if (phieuPhat.getPhieuMuon() == null
                || phieuPhat.getPhieuMuon().getNguoiMuon() == null
                || !docGia.getId().equals(phieuPhat.getPhieuMuon().getNguoiMuon().getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        if (phieuPhat.isPaid()) {
            return phieuPhatMapper.toPhieuPhatResponse(phieuPhat);
        }
        if (docGia.getBalance() < phieuPhat.getAmount()) {
            throw new AppException(ErrorCode.EBOOK_INSUFFICIENT_BALANCE);
        }

        docGia.setBalance(docGia.getBalance() - phieuPhat.getAmount());
        docGia.setTotalFines(Math.max(0, docGia.getTotalFines() - (int) phieuPhat.getAmount()));
        phieuPhat.setPaid(true);
        phieuPhat.setPaidAt(LocalDateTime.now());

        docGiaRepository.save(docGia);
        return phieuPhatMapper.toPhieuPhatResponse(phieuPhatRepository.save(phieuPhat));
    }

    public AdminRevenueResponse getAdminRevenue() {
        List<PhieuMua> purchases = phieuMuaRepository.findAll();
        List<PhieuPhat> fines = phieuPhatRepository.findAll();

        double ebookRevenue = purchases.stream()
                .mapToDouble(PhieuMua::getAmount)
                .sum();
        double fineRevenue = fines.stream()
                .filter(PhieuPhat::isPaid)
                .mapToDouble(PhieuPhat::getAmount)
                .sum();
        double pendingFineAmount = fines.stream()
                .filter(fine -> !fine.isPaid())
                .mapToDouble(PhieuPhat::getAmount)
                .sum();

        return AdminRevenueResponse.builder()
                .totalRevenue(ebookRevenue + fineRevenue)
                .ebookRevenue(ebookRevenue)
                .fineRevenue(fineRevenue)
                .ebookPurchaseCount(phieuMuaRepository.countByPurchaseTimeIsNotNull())
                .paidFineCount(phieuPhatRepository.countByPaidTrue())
                .pendingFineCount(phieuPhatRepository.countByPaidFalse())
                .pendingFineAmount(pendingFineAmount)
                .build();
    }

    private void validateReaderEligible(DocGia docGia, User user) {
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus()) || docGia.getTotalFines() > 0) {
            throw new AppException(ErrorCode.READER_NOT_ELIGIBLE);
        }
    }

    private void validateReservationRules(DocGia docGia, DauSach dauSach) {
        long activeReservations = phieuDatTruocRepository.countByDocGiaAndStatusIn(docGia, ACTIVE_RESERVATION_STATUSES);
        if (activeReservations >= MAX_ACTIVE_RESERVATIONS) throw new AppException(ErrorCode.MAX_RESERVATION_LIMIT);
        if (phieuDatTruocRepository.existsByDocGiaAndDauSachAndStatusIn(docGia, dauSach, ACTIVE_RESERVATION_STATUSES)) {
            throw new AppException(ErrorCode.DUPLICATE_ACTIVE_RESERVATION);
        }
        boolean isBorrowingThisTitle = chiTietPhieuMuonRepository.existsByPhieuMuon_NguoiMuonAndCuonSach_DauSachAndStatusNot(docGia, dauSach, "RETURNED");
        if (isBorrowingThisTitle) throw new AppException(ErrorCode.RESERVATION_FOR_BORROWED_TITLE);
        List<CuonSach> copies = cuonSachRepository.findAllByDauSach(dauSach);
        if (CollectionUtils.isEmpty(copies)) return;
        if (countAvailableCopies(copies) > 0) throw new AppException(ErrorCode.BOOK_AVAILABLE_DIRECT_BORROW);
    }

    private void validateRenewalRules(DocGia docGia, PhieuMuon phieuMuon, List<ChiTietPhieuMuon> activeDetails) {
        if (phieuMuon.getDueDate() == null || phieuMuon.getDueDate().isBefore(LocalDate.now())) {
            throw new AppException(ErrorCode.RENEWAL_OVERDUE_NOT_ALLOWED);
        }
        if (phieuMuon.getRenewalCount() >= MAX_RENEWAL_COUNT) {
            throw new AppException(ErrorCode.RENEWAL_LIMIT_REACHED);
        }
        boolean hasWaitingReservation = activeDetails.stream()
                .map(ChiTietPhieuMuon::getCuonSach)
                .filter(java.util.Objects::nonNull)
                .map(CuonSach::getDauSach)
                .filter(java.util.Objects::nonNull)
                .anyMatch(dauSach -> phieuDatTruocRepository.existsByDauSachAndDocGia_IdNotAndStatusIn(dauSach, docGia.getId(), ACTIVE_RESERVATION_STATUSES));
        if (hasWaitingReservation) throw new AppException(ErrorCode.RENEWAL_BLOCKED_BY_RESERVATION);
    }

    private void validateReservationPriorityForBorrow(PhieuMuon phieuMuon, CuonSach cuonSach) {
        if (phieuMuon == null || phieuMuon.getNguoiMuon() == null || cuonSach == null || cuonSach.getDauSach() == null) {
            return;
        }
        
        DauSach dauSach = cuonSach.getDauSach();
        
        // Đếm số phiếu đặt trước hoạt động
        long totalActiveReservations = phieuDatTruocRepository.countByDauSachAndStatusIn(dauSach, ACTIVE_RESERVATION_STATUSES);
        
        // Nếu không có phiếu đặt trước, không cần kiểm tra
        if (totalActiveReservations == 0) {
            return;
        }
        
        // Đếm số bản sao khả dụng
        List<CuonSach> allCopies = cuonSachRepository.findAllByDauSach(dauSach);
        int availableCopies = countAvailableCopies(allCopies);
        
        // Nếu số bản sao khả dụng >= số phiếu đặt trước, cho phép bất kỳ ai
        if (availableCopies >= totalActiveReservations) {
            return;
        }
        
        // Nếu số bản sao < số phiếu đặt trước, chỉ cho người ưu tiên đầu tiên
        phieuDatTruocRepository.findFirstByDauSachAndStatusInOrderByReservationDateAscIdAsc(dauSach, ACTIVE_RESERVATION_STATUSES)
                .ifPresent(firstReservation -> {
                    if (firstReservation.getDocGia() != null
                            && !firstReservation.getDocGia().getId().equals(phieuMuon.getNguoiMuon().getId())) {
                        throw new AppException(ErrorCode.RESERVATION_PRIORITY_REQUIRED);
                    }
                });
    }

    private void markReservationFulfilledIfNeeded(PhieuMuon phieuMuon, CuonSach cuonSach) {
        if (phieuMuon == null || phieuMuon.getNguoiMuon() == null || cuonSach == null || cuonSach.getDauSach() == null) {
            return;
        }
        phieuDatTruocRepository.findFirstByDauSachAndStatusInOrderByReservationDateAscIdAsc(cuonSach.getDauSach(), ACTIVE_RESERVATION_STATUSES)
                .ifPresent(firstReservation -> {
                    if (firstReservation.getDocGia() != null
                            && firstReservation.getDocGia().getId().equals(phieuMuon.getNguoiMuon().getId())) {
                        firstReservation.setStatus("DA_MUON");
                        phieuDatTruocRepository.save(firstReservation);
                    }
                });
    }

    private boolean hasReviewPermission(DocGia docGia, DauSach dauSach) {
        boolean borrowed = chiTietPhieuMuonRepository.existsByPhieuMuon_NguoiMuonAndCuonSach_DauSachAndStatusNot(docGia, dauSach, "NONE");
        EBook eBook = eBookRepository.findById(dauSach.getId()).orElse(null);
        boolean purchased = eBook != null && phieuMuaRepository.existsByDocGiaAndEBook(docGia, eBook);
        return borrowed || purchased;
    }

    private void recalculateAverageRating(DauSach dauSach) {
        List<DanhGia> reviews = danhGiaRepository.findAllByDauSach(dauSach);
        double average = reviews.stream().mapToInt(DanhGia::getRating).average().orElse(0.0);
        dauSach.setAverageRating(Math.round(average * 10.0) / 10.0);
        dauSachRepository.save(dauSach);
    }

    private boolean containsBannedWords(String comment) {
        if (!StringUtils.hasText(comment)) return false;
        String normalized = comment.toLowerCase();
        return BANNED_WORDS.stream().anyMatch(normalized::contains);
    }

    private int countAvailableCopies(List<CuonSach> copies) {
        return (int) copies.stream()
                .filter(CuonSach::isAvailable)
                .filter(copy -> !"THANH_LY".equalsIgnoreCase(copy.getStatus()))
                .count();
    }

    private DanhGiaResponse toDanhGiaResponse(DanhGia danhGia) {
        return DanhGiaResponse.builder()
                .id(danhGia.getId())
                .rating(danhGia.getRating())
                .comment(danhGia.getComment())
                .updatedAt(danhGia.getUpdatedAt())
                .fullName(danhGia.getDocGia().getFullName())
                .dauSachId(danhGia.getDauSach().getId())
                .build();
    }

    private LichSuMuonTraItemResponse toBorrowHistoryItem(ChiTietPhieuMuon detail) {
        PhieuMuon phieuMuon = detail.getPhieuMuon();
        LocalDate dueDate = getEffectiveDueDate(detail);
        String renewBlockedReason = getRenewBlockedReason(detail);
        boolean overdue = phieuMuon != null
                && dueDate != null
                && dueDate.isBefore(LocalDate.now())
                && !isReturned(detail);

        return LichSuMuonTraItemResponse.builder()
                .id(detail.getId())
                .phieuMuonId(phieuMuon != null ? phieuMuon.getId() : null)
                .cuonSachBarcode(detail.getCuonSach() != null ? detail.getCuonSach().getBarcode() : null)
                .bookTitle(detail.getCuonSach() != null && detail.getCuonSach().getDauSach() != null ? detail.getCuonSach().getDauSach().getTitle() : null)
                .borrowDate(phieuMuon != null ? phieuMuon.getBorrowDate() : null)
                .dueDate(dueDate)
                .returnDate(detail.getReturnDate())
                .status(detail.getStatus())
                .renewalCount(detail.getRenewalCount())
                .overdue(overdue)
                .canRenew(!StringUtils.hasText(renewBlockedReason))
                .renewBlockedReason(renewBlockedReason)
                .build();
    }

    private LichSuMuonTraItemResponse toBorrowHistoryTicketItem(List<ChiTietPhieuMuon> details) {
        ChiTietPhieuMuon first = details.get(0);
        PhieuMuon phieuMuon = first.getPhieuMuon();
        List<String> titles = details.stream()
                .map(detail -> detail.getCuonSach() != null && detail.getCuonSach().getDauSach() != null ? detail.getCuonSach().getDauSach().getTitle() : null)
                .filter(StringUtils::hasText)
                .distinct()
                .toList();
        boolean allReturned = details.stream().allMatch(this::isReturned);
        boolean overdue = !allReturned && phieuMuon != null && phieuMuon.getDueDate() != null && phieuMuon.getDueDate().isBefore(LocalDate.now());
        String renewBlockedReason = getRenewBlockedReason(phieuMuon, details);

        return LichSuMuonTraItemResponse.builder()
                .id(first.getId())
                .phieuMuonId(phieuMuon != null ? phieuMuon.getId() : null)
                .cuonSachBarcode(null)
                .bookTitle(String.join(", ", titles))
                .borrowDate(phieuMuon != null ? phieuMuon.getBorrowDate() : null)
                .dueDate(phieuMuon != null ? phieuMuon.getDueDate() : null)
                .returnDate(allReturned ? details.stream().map(ChiTietPhieuMuon::getReturnDate).filter(java.util.Objects::nonNull).max(LocalDate::compareTo).orElse(null) : null)
                .status(allReturned ? "DA_TRA" : "BORROWING")
                .renewalCount(phieuMuon != null ? phieuMuon.getRenewalCount() : 0)
                .overdue(overdue)
                .canRenew(!StringUtils.hasText(renewBlockedReason))
                .renewBlockedReason(renewBlockedReason)
                .build();
    }

    private boolean isReturned(ChiTietPhieuMuon detail) {
        if (detail.getReturnDate() != null) return true;
        return StringUtils.hasText(detail.getStatus())
                && ("RETURNED".equalsIgnoreCase(detail.getStatus()) || "DA_TRA".equalsIgnoreCase(detail.getStatus()));
    }

    private LocalDate getEffectiveDueDate(ChiTietPhieuMuon detail) {
        if (detail.getDueDate() != null) return detail.getDueDate();
        return detail.getPhieuMuon() != null ? detail.getPhieuMuon().getDueDate() : null;
    }

    private String getRenewBlockedReason(ChiTietPhieuMuon detail) {
        if (isReturned(detail)) return "Cuốn sách này đã được trả.";
        LocalDate dueDate = getEffectiveDueDate(detail);
        if (dueDate == null || dueDate.isBefore(LocalDate.now())) {
            return "Sách đã quá hạn, bạn cần đến thư viện để xử lý.";
        }
        if (detail.getRenewalCount() >= MAX_RENEWAL_COUNT) {
            return "Bạn đã dùng hết 2 lượt gia hạn cho cuốn sách này.";
        }
        DocGia currentDocGia = getCurrentDocGiaOrNull();
        DauSach dauSach = detail.getCuonSach() != null ? detail.getCuonSach().getDauSach() : null;
        if (currentDocGia != null
                && dauSach != null
                && phieuDatTruocRepository.existsByDauSachAndDocGia_IdNotAndStatusIn(dauSach, currentDocGia.getId(), ACTIVE_RESERVATION_STATUSES)) {
            return "Đầu sách này đã có người đặt trước.";
        }
        return null;
    }

    private String getRenewBlockedReason(PhieuMuon phieuMuon, List<ChiTietPhieuMuon> details) {
        if (phieuMuon == null) return "Không tìm thấy phiếu mượn.";
        if (details.stream().allMatch(this::isReturned)) return "Phiếu mượn này đã được trả xong.";
        if (phieuMuon.getDueDate() == null || phieuMuon.getDueDate().isBefore(LocalDate.now())) {
            return "Phiếu mượn đã quá hạn, bạn cần đến thư viện để xử lý.";
        }
        if (phieuMuon.getRenewalCount() >= MAX_RENEWAL_COUNT) {
            return "Bạn đã dùng hết 2 lượt gia hạn cho phiếu mượn này.";
        }
        DocGia currentDocGia = getCurrentDocGiaOrNull();
        boolean waitingReservation = currentDocGia != null && details.stream()
                .filter(detail -> !isReturned(detail))
                .map(ChiTietPhieuMuon::getCuonSach)
                .filter(java.util.Objects::nonNull)
                .map(CuonSach::getDauSach)
                .filter(java.util.Objects::nonNull)
                .anyMatch(dauSach -> phieuDatTruocRepository.existsByDauSachAndDocGia_IdNotAndStatusIn(dauSach, currentDocGia.getId(), ACTIVE_RESERVATION_STATUSES));
        if (waitingReservation) {
            return "Một đầu sách trong phiếu này đã có người đặt trước.";
        }
        return null;
    }

    private PhieuDatTruocResponse toReservationResponse(PhieuDatTruoc reservation) {
        PhieuDatTruocResponse response = phieuDatTruocMapper.toPhieuDatTruocResponse(reservation);
        List<PhieuDatTruoc> queue = phieuDatTruocRepository.findAllByDauSachAndStatusInOrderByReservationDateAscIdAsc(reservation.getDauSach(), ACTIVE_RESERVATION_STATUSES);
        int queuePosition = queue.indexOf(reservation) + 1;
        response.setQueuePosition(queuePosition > 0 ? queuePosition : null);
        return response;
    }

    private TraCuuTaiLieuResponse toTraCuuTaiLieuResponse(DauSach dauSach) {
        int availableCount = countAvailableCopies(cuonSachRepository.findAllByDauSach(dauSach));
        EBook eBook = findEBookSafely(dauSach.getId());
        return TraCuuTaiLieuResponse.builder()
                .id(dauSach.getId())
                .title(dauSach.getTitle())
                .author(dauSach.getAuthor())
                .category(dauSach.getCategory())
                .publishYear(dauSach.getPublishYear())
                .coverImageUrl(dauSach.getCoverImageUrl())
                .averageRating(dauSach.getAverageRating())
                .availableCount(availableCount)
                .tinhTrang(buildAvailabilityText(availableCount, eBook))
                .hasEBook(eBook != null && StringUtils.hasText(eBook.getAccessLink()))
                .eBookLink(null)
                .build();
    }

    private String buildAvailabilityText(int availableCount, EBook eBook) {
        if (availableCount > 0) return "Còn " + availableCount + " cuốn";
        if (eBook != null && StringUtils.hasText(eBook.getAccessLink())) return "Có E-Book";
        return "Hết sách";
    }

    private boolean matchesKeyword(DauSach dauSach, String keyword) {
        if (!StringUtils.hasText(keyword)) return true;
        String normalizedKeyword = keyword.trim().toLowerCase();
        return containsIgnoreCase(dauSach.getTitle(), normalizedKeyword)
                || containsIgnoreCase(dauSach.getAuthor(), normalizedKeyword)
                || containsIgnoreCase(dauSach.getCategory(), normalizedKeyword);
    }

    private boolean matchesThuThuKeyword(DauSach dauSach, String keyword) {
        if (!StringUtils.hasText(keyword)) return true;
        String normalizedKeyword = keyword.trim().toLowerCase();
        return containsIgnoreCase(dauSach.getTitle(), normalizedKeyword)
                || containsIgnoreCase(dauSach.getAuthor(), normalizedKeyword);
    }

    private boolean matchesField(String field, String expected) {
        if (!StringUtils.hasText(expected)) return true;
        return containsIgnoreCase(field, expected.trim().toLowerCase());
    }

    private boolean matchesCategory(String field, String expected) {
        if (!StringUtils.hasText(expected)) return true;
        return field != null && field.equalsIgnoreCase(expected.trim());
    }

    private boolean containsIgnoreCase(String value, String keyword) {
        return value != null && value.toLowerCase().contains(keyword);
    }

    private TraCuuThuThuItemResponse toThuThuLookupItem(DauSach dauSach) {
        List<CuonSach> copies = cuonSachRepository.findAllByDauSach(dauSach);
        List<CuonSach> activeCopies = copies.stream()
                .filter(copy -> !"THANH_LY".equalsIgnoreCase(copy.getStatus()))
                .toList();
        String defaultLocation = ShelfLocationCatalog.buildBookLocation(dauSach.getFloorNumber(), dauSach.getCategory());
        return TraCuuThuThuItemResponse.builder()
                .id(dauSach.getId())
                .title(dauSach.getTitle())
                .author(dauSach.getAuthor())
                .category(dauSach.getCategory())
                .description(dauSach.getDescription())
                .longIntroduction(dauSach.getLongIntroduction())
                .floorNumber(dauSach.getFloorNumber())
                .shelfCode(dauSach.getShelfCode())
                .defaultLocation(defaultLocation)
                .coverImageUrl(dauSach.getCoverImageUrl())
                .publishYear(dauSach.getPublishYear())
                .shelfLocations(StringUtils.hasText(defaultLocation) ? List.of(defaultLocation) : List.of())
                .availableBarcodes(activeCopies.stream()
                        .filter(CuonSach::isAvailable)
                        .map(CuonSach::getBarcode)
                        .toList())
                .totalCopies(activeCopies.size())
                .availableCopies((int) activeCopies.stream().filter(copy -> "AVAILABLE".equalsIgnoreCase(copy.getStatus()) || "SAN_SANG".equalsIgnoreCase(copy.getStatus()) || copy.isAvailable()).count())
                .borrowedCopies((int) activeCopies.stream().filter(copy -> "BORROWED".equalsIgnoreCase(copy.getStatus()) || "BORROWING".equalsIgnoreCase(copy.getStatus()) || "DANG_MUON".equalsIgnoreCase(copy.getStatus()) || (!copy.isAvailable() && !"LOST".equalsIgnoreCase(copy.getStatus()))).count())
                .lostCopies((int) activeCopies.stream().filter(copy -> "LOST".equalsIgnoreCase(copy.getStatus())).count())
                .damagedCopies((int) activeCopies.stream().filter(copy -> "DAMAGED".equalsIgnoreCase(copy.getStatus()) || "DAMAGED".equalsIgnoreCase(copy.getPhysicalCondition()) || "WORN".equalsIgnoreCase(copy.getPhysicalCondition())).count())
                .build();
    }

    private void validateInventoryCreateRequest(QuanLyKhoCreateRequest request) {
        if (!StringUtils.hasText(request.getTitle()) || !StringUtils.hasText(request.getAuthor())) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        if (request.getCopyCount() == null || request.getCopyCount() < 0) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        request.setCategory(normalizeCategory(request.getCategory()));
        validateFloor(request.getFloorNumber());
    }

    private void syncEBook(DauSach dauSach, String accessLink, String format, Double fileSize, Double price,
                           Boolean premiumOnly, Boolean downloadable, Boolean underMaintenance) {
        EBook existing = eBookRepository.findById(dauSach.getId()).orElse(null);
        if (!StringUtils.hasText(accessLink)) {
            if (existing != null) {
                eBookRepository.delete(existing);
            }
            return;
        }

        EBook eBook = existing != null ? existing : EBook.builder()
                .id(dauSach.getId())
                .dauSach(dauSach)
                .build();
        eBook.setDauSach(dauSach);
        eBook.setAccessLink(accessLink);
        eBook.setFormat(StringUtils.hasText(format) ? format : "PDF");
        eBook.setFileSize(fileSize == null ? 0 : fileSize);
        eBook.setPrice(price == null ? 0 : Math.max(0, price));
        eBook.setPremiumOnly(Boolean.TRUE.equals(premiumOnly));
        eBook.setDownloadable(downloadable == null || downloadable);
        eBook.setUnderMaintenance(Boolean.TRUE.equals(underMaintenance));
        eBookRepository.save(eBook);
    }

    private DauSachResponse toDauSachResponseWithEBook(DauSach dauSach) {
        DauSachResponse response = dauSachMapper.toDauSachResponse(dauSach);
        EBook eBook = findEBookSafely(dauSach.getId());
        if (eBook == null) {
            response.setHasEBook(false);
            return response;
        }
        response.setHasEBook(StringUtils.hasText(eBook.getAccessLink()));
        response.setEBookLink(eBook.getAccessLink());
        response.setEBookFormat(eBook.getFormat());
        response.setEBookFileSize(eBook.getFileSize());
        response.setEBookPrice(eBook.getPrice());
        response.setEBookPremiumOnly(eBook.isPremiumOnly());
        response.setEBookDownloadable(eBook.isDownloadable());
        response.setEBookUnderMaintenance(eBook.isUnderMaintenance());
        return response;
    }

    private String normalizeCategory(String category) {
        if (!StringUtils.hasText(category)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return BookCategoryCatalog.CATEGORIES.stream()
                .filter(item -> item.equalsIgnoreCase(category.trim()))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
    }

    private void validateFloor(Integer floorNumber) {
        if (!ShelfLocationCatalog.isValidFloor(floorNumber)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
    }

    private String generateLocationBasedBarcode(String category, Integer floorNumber, String shelfCode) {
        String categoryCode = buildCategoryCode(category);
        String floorCode = buildFloorCode(floorNumber);
        String shelfSegment = buildShelfSegment(category, shelfCode);
        String prefix = floorCode + "-" + shelfSegment + "-" + categoryCode + "-";
        int nextSequence = cuonSachRepository.findAllByBarcodeStartingWith(prefix).stream()
                .map(CuonSach::getBarcode)
                .map(code -> code.substring(prefix.length()))
                .map(sequence -> {
                    try {
                        return Integer.parseInt(sequence);
                    } catch (NumberFormatException exception) {
                        return 0;
                    }
                })
                .max(Integer::compareTo)
                .orElse(0) + 1;
        return prefix + String.format("%03d", nextSequence);
    }

    private String buildCategoryCode(String category) {
        String normalized = Normalizer.normalize(category, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace('đ', 'd')
                .replace('Đ', 'D');
        String[] tokens = normalized.toUpperCase()
                .split("[^A-Z0-9]+");
        StringBuilder builder = new StringBuilder();
        for (String token : tokens) {
            if (!token.isBlank()) {
                builder.append(token.charAt(0));
            }
        }
        if (builder.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return builder.toString();
    }

    private String buildFloorCode(Integer floorNumber) {
        if (!ShelfLocationCatalog.isValidFloor(floorNumber)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return "T" + String.format("%02d", floorNumber);
    }

    private String buildShelfSegment(String category, String shelfCode) {
        if (!ShelfLocationCatalog.isValidShelfCode(category, shelfCode)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return "K" + shelfCode.toUpperCase();
    }

    private DocGia getCurrentDocGia() {
        DocGia docGia = getCurrentDocGiaOrNull();
        if (docGia == null) throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        return docGia;
    }

    private DocGia getCurrentDocGiaOrNull() {
        try {
            User user = userService.getMyInfo();
            return docGiaRepository.findByUser(user);
        } catch (Exception exception) {
            return null;
        }
    }

    private EBook findEBookSafely(String id) {
        try {
            return eBookRepository.findById(id).orElse(null);
        } catch (Exception exception) {
            return null;
        }
    }

    private String safeBuildBookLocation(Integer floorNumber, String category) {
        try {
            return ShelfLocationCatalog.buildBookLocation(floorNumber, category);
        } catch (Exception exception) {
            return null;
        }
    }

    private DauSach findDauSachById(String id) {
        return dauSachRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DAUSACH));
    }

    private CuonSach findCuonSachById(String barcode) {
        return cuonSachRepository.findById(barcode).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CUONSACH));
    }

    private PhieuMuon findPhieuMuonById(String id) {
        return phieuMuonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_MUON));
    }

    private boolean hasBorrowDetails(PhieuMuon phieuMuon) {
        return phieuMuon != null
                && !CollectionUtils.isEmpty(chiTietPhieuMuonRepository.findAllByPhieuMuonOrderByIdAsc(phieuMuon));
    }
}

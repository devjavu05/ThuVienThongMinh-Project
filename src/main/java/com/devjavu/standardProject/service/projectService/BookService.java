package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.CuonSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachCreationRequest;
import com.devjavu.standardProject.dto.request.projectRequest.bookManagerRequest.DauSachUpdateRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.ChiTietPhieuMuonRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuDatTruocRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuMuonRequest;
import com.devjavu.standardProject.dto.request.projectRequest.buisinessTransactionsRequest.PhieuPhatRequest;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.DauSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.ChiTietPhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuDatTruocResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuPhatResponse;
import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.ChiTietPhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuDatTruoc;
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
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.ChiTietPhieuMuonRepository;
import com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo.PhieuDatTruocRepository;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookService {
    DauSachRepository dauSachRepository;
    DauSachMapper dauSachMapper;
    CuonSachMapper cuonSachMapper;
    CuonSachRepository cuonSachRepository;
    PhieuMuonRepository phieuMuonRepository;
    ChiTietPhieuMuonRepository chiTietPhieuMuonRepository;
    PhieuDatTruocRepository phieuDatTruocRepository;
    PhieuPhatRepository phieuPhatRepository;
    PhieuMuonMapper phieuMuonMapper;
    ChiTietPhieuMuonMapper chiTietPhieuMuonMapper;
    PhieuDatTruocMapper phieuDatTruocMapper;
    PhieuPhatMapper phieuPhatMapper;
    UserService userService;
    NhanVienRepository nhanVienRepository;
    DocGiaRepository docGiaRepository;

    public List<DauSachResponse> getDauSach() {
        return dauSachRepository.findAll()
                .stream().map(dauSachMapper::toDauSachResponse).toList();
    }

    public DauSachResponse createDauSach(DauSachCreationRequest request) {
        DauSach dauSach = dauSachMapper.toDauSach(request);
        dauSach.setQuantity(0);
        return dauSachMapper.toDauSachResponse(dauSachRepository.save(dauSach));
    }

    public List<CuonSachResponse> getCuonSach(String id) {
        DauSach dauSach = findDauSachById(id);
        return cuonSachRepository.findAllByDauSach(dauSach).stream()
                .map(cuonSachMapper::toCuonSachResponse)
                .toList();
    }

    public CuonSachResponse createCuonSach(CuonSachCreationRequest request) {
        CuonSach cuonSach = cuonSachMapper.toCuonSach(request);
        DauSach dauSach = findDauSachById(request.getDauSach());
        dauSach.setQuantity(dauSach.getQuantity() + 1);
        cuonSach.setDauSach(dauSach);
        cuonSach.setAvailable(true);
        cuonSach.setStt(dauSach.getQuantity());
        return cuonSachMapper.toCuonSachResponse(cuonSachRepository.save(cuonSach));
    }

    public DauSachResponse updateDauSach(DauSachUpdateRequest request, String id) {
        DauSach dauSach = findDauSachById(id);
        dauSachMapper.updateDauSach(dauSach, request);
        return dauSachMapper.toDauSachResponse(dauSachRepository.save(dauSach));
    }

    public DauSachResponse getDauSachById(String id) {
        return dauSachMapper.toDauSachResponse(findDauSachById(id));
    }

    public PhieuMuonResponse createPhieuMuon(PhieuMuonRequest request) {
        PhieuMuon phieuMuon = phieuMuonMapper.toPhieuMuon(request);
        User user = userService.getMyInfo();
        NhanVien nhanVien = nhanVienRepository.findByUser(user);
        DocGia docGia = docGiaRepository.findByEmail(request.getEmail());
        if (docGia == null) {
            throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        }
        phieuMuon.setNguoiMuon(docGia);
        phieuMuon.setNhanVien(nhanVien);
        return phieuMuonMapper.toPhieuMuonResponse(phieuMuonRepository.save(phieuMuon));
    }

    public List<PhieuMuonResponse> getPhieuMuon() {
        return phieuMuonRepository.findAll().stream()
                .map(phieuMuonMapper::toPhieuMuonResponse)
                .toList();
    }

    public PhieuMuonResponse getPhieuMuonById(String id) {
        return phieuMuonMapper.toPhieuMuonResponse(findPhieuMuonById(id));
    }

    public void deletePhieuMuon(String id) {
        phieuMuonRepository.delete(findPhieuMuonById(id));
    }

    public ChiTietPhieuMuonResponse createChiTietPhieuMuon(ChiTietPhieuMuonRequest request) {
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonMapper.toChiTietPhieuMuon(request);
        PhieuMuon phieuMuon = findPhieuMuonById(request.getPhieuMuonId());
        CuonSach cuonSach = findCuonSachById(request.getCuonSachBarcode());
        chiTietPhieuMuon.setPhieuMuon(phieuMuon);
        chiTietPhieuMuon.setCuonSach(cuonSach);
        cuonSach.setAvailable(false);
        cuonSachRepository.save(cuonSach);
        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(chiTietPhieuMuonRepository.save(chiTietPhieuMuon));
    }

    public List<ChiTietPhieuMuonResponse> getChiTietPhieuMuon() {
        return chiTietPhieuMuonRepository.findAll().stream()
                .map(chiTietPhieuMuonMapper::toChiTietPhieuMuonResponse)
                .toList();
    }

    public ChiTietPhieuMuonResponse getChiTietPhieuMuonById(Long id) {
        ChiTietPhieuMuon chiTietPhieuMuon = chiTietPhieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CHI_TIET_PHIEU_MUON));
        return chiTietPhieuMuonMapper.toChiTietPhieuMuonResponse(chiTietPhieuMuon);
    }

    public PhieuDatTruocResponse createPhieuDatTruoc(PhieuDatTruocRequest request) {
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocMapper.toPhieuDatTruoc(request);
        DocGia docGia = docGiaRepository.findByEmail(request.getEmail());
        if (docGia == null) {
            throw new AppException(ErrorCode.NOT_FOUND_DOCGIA);
        }
        DauSach dauSach = findDauSachById(request.getDauSachId());
        phieuDatTruoc.setDocGia(docGia);
        phieuDatTruoc.setDauSach(dauSach);
        return phieuDatTruocMapper.toPhieuDatTruocResponse(phieuDatTruocRepository.save(phieuDatTruoc));
    }

    public List<PhieuDatTruocResponse> getPhieuDatTruoc() {
        return phieuDatTruocRepository.findAll().stream()
                .map(phieuDatTruocMapper::toPhieuDatTruocResponse)
                .toList();
    }

    public PhieuDatTruocResponse getPhieuDatTruocById(String id) {
        PhieuDatTruoc phieuDatTruoc = phieuDatTruocRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_DAT_TRUOC));
        return phieuDatTruocMapper.toPhieuDatTruocResponse(phieuDatTruoc);
    }

    public PhieuPhatResponse createPhieuPhat(PhieuPhatRequest request) {
        PhieuPhat phieuPhat = phieuPhatMapper.toPhieuPhat(request);
        PhieuMuon phieuMuon = findPhieuMuonById(request.getPhieuMuonId());
        CuonSach cuonSach = findCuonSachById(request.getCuonSachBarcode());
        phieuPhat.setPhieuMuon(phieuMuon);
        phieuPhat.setCuonSach(cuonSach);

        DocGia docGia = phieuMuon.getNguoiMuon();
        docGia.setTotalFines(docGia.getTotalFines() + (int) request.getAmount());
        docGiaRepository.save(docGia);

        return phieuPhatMapper.toPhieuPhatResponse(phieuPhatRepository.save(phieuPhat));
    }

    public List<PhieuPhatResponse> getPhieuPhat() {
        return phieuPhatRepository.findAll().stream()
                .map(phieuPhatMapper::toPhieuPhatResponse)
                .toList();
    }

    public PhieuPhatResponse getPhieuPhatById(String id) {
        PhieuPhat phieuPhat = phieuPhatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_PHAT));
        return phieuPhatMapper.toPhieuPhatResponse(phieuPhat);
    }

    private DauSach findDauSachById(String id) {
        return dauSachRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DAUSACH));
    }

    private CuonSach findCuonSachById(String barcode) {
        return cuonSachRepository.findById(barcode)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CUONSACH));
    }

    private PhieuMuon findPhieuMuonById(String id) {
        return phieuMuonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_PHIEU_MUON));
    }
}

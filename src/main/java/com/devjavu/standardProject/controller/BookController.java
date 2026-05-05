package com.devjavu.standardProject.controller;

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
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.DauSachResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuTaiLieuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.ChiTietTaiLieuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.CuonSachLookupResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.ChiTietPhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.DanhGiaResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.LichSuMuonTraResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuDatTruocResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuaResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuMuonResponse;
import com.devjavu.standardProject.dto.response.projectResponse.buisinessTransactionsResponse.PhieuPhatResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuThuThuResponse;
import com.devjavu.standardProject.dto.response.projectResponse.bookManagerResponse.TraCuuThuThuItemResponse;
import com.devjavu.standardProject.dto.response.standardResponse.ApiResponse;
import com.devjavu.standardProject.service.projectService.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/books")
@Tag(name = "Books")
@SecurityRequirement(name = "bearerAuth")
public class BookController {
    BookService bookService;
    
    @PreAuthorize("hasAnyAuthority('CREATE_DAU_SACH','UPDATE_DAU_SACH')")
    @PostMapping("/cover-upload")
    public ApiResponse<String> uploadBookCover(@RequestParam("file") MultipartFile file) {
        return ApiResponse.<String>builder()
                .result(bookService.uploadBookCover(file))
                .build();
    }

    @Operation(summary = "Lay danh sach dau sach", security = {})
    @GetMapping
    public ApiResponse<List<DauSachResponse>> getDauSach() {
        return ApiResponse.<List<DauSachResponse>>builder()
                .result(bookService.getDauSach())
                .build();
    }

    @Operation(summary = "Tra cuu tai lieu cong khai", security = {})
    @GetMapping("/search")
    public ApiResponse<List<TraCuuTaiLieuResponse>> traCuuTaiLieu(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer publishYear
    ) {
        return ApiResponse.<List<TraCuuTaiLieuResponse>>builder()
                .result(bookService.traCuuTaiLieu(q, author, category, publishYear))
                .build();
    }

    @Operation(summary = "Tra cuu tai lieu cho thu thu", security = {})
    @GetMapping("/staff-lookup")
    public ApiResponse<TraCuuThuThuResponse> traCuuThuThu(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<TraCuuThuThuResponse>builder()
                .result(bookService.traCuuThuThu(q, category, page, size))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_CUON_SACH')")
    @GetMapping("/inventory")
    public ApiResponse<TraCuuThuThuResponse> getInventory(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<TraCuuThuThuResponse>builder()
                .result(bookService.traCuuThuThu(q, category, page, size))
                .build();
    }

    @Operation(summary = "Tra cuu nhanh bang ma vach cho thu thu", security = {})
    @GetMapping("/copies/{barcode}/lookup")
    public ApiResponse<CuonSachLookupResponse> traCuuCuonSachTheoBarcode(@PathVariable String barcode) {
        return ApiResponse.<CuonSachLookupResponse>builder()
                .result(bookService.traCuuCuonSachTheoBarcode(barcode))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_DAU_SACH')")
    @PostMapping
    public ApiResponse<DauSachResponse> createDauSach(@RequestBody DauSachCreationRequest request) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.createDauSach(request))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_DAU_SACH')")
    @PostMapping("/inventory")
    public ApiResponse<TraCuuThuThuItemResponse> createInventoryBook(@RequestBody QuanLyKhoCreateRequest request) {
        return ApiResponse.<TraCuuThuThuItemResponse>builder()
                .result(bookService.createInventoryBook(request))
                .build();
    }

    @PreAuthorize("hasAuthority('UPDATE_DAU_SACH')")
    @PutMapping("{id}")
    public ApiResponse<DauSachResponse> updateDauSach(@RequestBody DauSachUpdateRequest request, @PathVariable String id) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.updateDauSach(request, id))
                .build();
    }

    @PreAuthorize("hasAuthority('UPDATE_DAU_SACH')")
    @DeleteMapping("{id}")
    public ApiResponse<String> deleteDauSach(@PathVariable String id) {
        bookService.deleteDauSach(id);
        return ApiResponse.<String>builder()
                .result("deleted")
                .build();
    }

    @Operation(summary = "Lay chi tiet dau sach", security = {})
    @GetMapping("/{id}")
    public ApiResponse<DauSachResponse> getDauSachById(@PathVariable String id) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.getDauSachById(id))
                .build();
    }

    @Operation(summary = "Lay chi tiet tai lieu cho doc gia", security = {})
    @GetMapping("/{id}/lookup")
    public ApiResponse<ChiTietTaiLieuResponse> getChiTietTaiLieu(@PathVariable String id) {
        return ApiResponse.<ChiTietTaiLieuResponse>builder()
                .result(bookService.getChiTietTaiLieu(id))
                .build();
    }

    @Operation(summary = "Lay danh gia cua dau sach", security = {})
    @GetMapping("/{id}/reviews")
    public ApiResponse<List<DanhGiaResponse>> getReviews(@PathVariable String id) {
        return ApiResponse.<List<DanhGiaResponse>>builder()
                .result(bookService.getDanhGiaByBook(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PostMapping("/{id}/reviews")
    public ApiResponse<DanhGiaResponse> submitReview(@PathVariable String id, @RequestBody DanhGiaRequest request) {
        return ApiResponse.<DanhGiaResponse>builder()
                .result(bookService.submitDanhGia(id, request))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_CUON_SACH')")
    @PostMapping("/cuonSach")
    public ApiResponse<CuonSachResponse> createCuonSach(@RequestBody CuonSachCreationRequest request) {
        return ApiResponse.<CuonSachResponse>builder()
                .result(bookService.createCuonSach(request))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_CUON_SACH')")
    @GetMapping("/{id}/cuonSach")
    public ApiResponse<List<CuonSachResponse>> getCuonSach(@PathVariable String id) {
        return ApiResponse.<List<CuonSachResponse>>builder()
                .result(bookService.getCuonSach(id))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_CUON_SACH')")
    @PostMapping("/{id}/copies")
    public ApiResponse<List<CuonSachResponse>> createCopiesForExistingBook(@PathVariable String id, @RequestBody ThemBanSaoRequest request) {
        return ApiResponse.<List<CuonSachResponse>>builder()
                .result(bookService.createCopiesForExistingBook(id, request))
                .build();
    }

    @PreAuthorize("hasAuthority('UPDATE_DAU_SACH')")
    @PatchMapping("/copies/{barcode}/liquidate")
    public ApiResponse<CuonSachResponse> liquidateCopy(@PathVariable String barcode) {
        return ApiResponse.<CuonSachResponse>builder()
                .result(bookService.liquidateCopy(barcode))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_MUON')")
    @PostMapping("/tickets")
    public ApiResponse<PhieuMuonResponse> createPhieuMuon(@RequestBody PhieuMuonRequest request) {
        return ApiResponse.<PhieuMuonResponse>builder()
                .result(bookService.createPhieuMuon(request))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_MUON')")
    @GetMapping("/tickets")
    public ApiResponse<List<PhieuMuonResponse>> getPhieuMuon() {
        return ApiResponse.<List<PhieuMuonResponse>>builder()
                .result(bookService.getPhieuMuon())
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_MUON')")
    @GetMapping("/tickets/search")
    public ApiResponse<List<PhieuMuonResponse>> searchPhieuMuon(
            @RequestParam(required = false) String nguoiMuonName,
            @RequestParam(required = false) String borrowDateFrom,
            @RequestParam(required = false) String borrowDateTo
    ) {
        java.time.LocalDate fromDate = borrowDateFrom != null && !borrowDateFrom.isEmpty() 
            ? java.time.LocalDate.parse(borrowDateFrom) 
            : null;
        java.time.LocalDate toDate = borrowDateTo != null && !borrowDateTo.isEmpty() 
            ? java.time.LocalDate.parse(borrowDateTo) 
            : null;
        return ApiResponse.<List<PhieuMuonResponse>>builder()
                .result(bookService.searchPhieuMuon(nguoiMuonName, fromDate, toDate))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_MUON')")
    public ApiResponse<PhieuMuonResponse> getPhieuMuonById(@PathVariable String id) {
        return ApiResponse.<PhieuMuonResponse>builder()
                .result(bookService.getPhieuMuonById(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PatchMapping("/tickets/{id}/renew")
    public ApiResponse<PhieuMuonResponse> renewPhieuMuon(@PathVariable String id) {
        return ApiResponse.<PhieuMuonResponse>builder()
                .result(bookService.renewPhieuMuon(id))
                .build();
    }

    @PreAuthorize("hasAuthority('DELETE_PHIEU_MUON')")
    @DeleteMapping("/tickets/{id}")
    public ApiResponse<String> deletePhieuMuon(@PathVariable String id) {
        bookService.deletePhieuMuon(id);
        return ApiResponse.<String>builder()
                .result("deleted")
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_CHI_TIET_PHIEU_MUON')")
    @PatchMapping("/tickets/{id}/finalize")
    public ApiResponse<PhieuMuonResponse> finalizePhieuMuon(@PathVariable String id) {
        return ApiResponse.<PhieuMuonResponse>builder()
                .result(bookService.finalizePhieuMuon(id))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_CHI_TIET_PHIEU_MUON')")
    @PostMapping("/ticket-details")
    public ApiResponse<ChiTietPhieuMuonResponse> createChiTietPhieuMuon(@RequestBody ChiTietPhieuMuonRequest request) {
        return ApiResponse.<ChiTietPhieuMuonResponse>builder()
                .result(bookService.createChiTietPhieuMuon(request))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_CHI_TIET_PHIEU_MUON')")
    @GetMapping("/ticket-details")
    public ApiResponse<List<ChiTietPhieuMuonResponse>> getChiTietPhieuMuon() {
        return ApiResponse.<List<ChiTietPhieuMuonResponse>>builder()
                .result(bookService.getChiTietPhieuMuon())
                .build();
    }

    @PreAuthorize("hasAuthority('GET_CHI_TIET_PHIEU_MUON')")
    @GetMapping("/ticket-details/{id}")
    public ApiResponse<ChiTietPhieuMuonResponse> getChiTietPhieuMuonById(@PathVariable Long id) {
        return ApiResponse.<ChiTietPhieuMuonResponse>builder()
                .result(bookService.getChiTietPhieuMuonById(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PatchMapping("/ticket-details/{id}/renew")
    public ApiResponse<ChiTietPhieuMuonResponse> renewChiTietPhieuMuon(@PathVariable Long id) {
        return ApiResponse.<ChiTietPhieuMuonResponse>builder()
                .result(bookService.renewChiTietPhieuMuon(id))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_CHI_TIET_PHIEU_MUON')")
    @PatchMapping("/ticket-details/{id}/return")
    public ApiResponse<ChiTietPhieuMuonResponse> returnChiTietPhieuMuon(@PathVariable Long id) {
        return ApiResponse.<ChiTietPhieuMuonResponse>builder()
                .result(bookService.returnChiTietPhieuMuon(id))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_DAT_TRUOC')")
    @PostMapping("/reservations")
    public ApiResponse<PhieuDatTruocResponse> createPhieuDatTruoc(@RequestBody PhieuDatTruocRequest request) {
        return ApiResponse.<PhieuDatTruocResponse>builder()
                .result(bookService.createPhieuDatTruoc(request))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_DAT_TRUOC')")
    @GetMapping("/reservations")
    public ApiResponse<List<PhieuDatTruocResponse>> getPhieuDatTruoc() {
        return ApiResponse.<List<PhieuDatTruocResponse>>builder()
                .result(bookService.getPhieuDatTruoc())
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_DAT_TRUOC')")
    @GetMapping("/reservations/{id}")
    public ApiResponse<PhieuDatTruocResponse> getPhieuDatTruocById(@PathVariable String id) {
        return ApiResponse.<PhieuDatTruocResponse>builder()
                .result(bookService.getPhieuDatTruocById(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping("/reader/borrow-history")
    public ApiResponse<LichSuMuonTraResponse> getMyBorrowHistory() {
        return ApiResponse.<LichSuMuonTraResponse>builder()
                .result(bookService.getMyBorrowHistory())
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_DAT_TRUOC')")
    @PatchMapping("/reservations/{id}/cancel")
    public ApiResponse<String> cancelPhieuDatTruoc(@PathVariable String id) {
        bookService.cancelPhieuDatTruoc(id);
        return ApiResponse.<String>builder()
                .result("cancelled")
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_DAT_TRUOC')")
    @DeleteMapping("/reservations/{id}")
    public ApiResponse<String> deletePhieuDatTruoc(@PathVariable String id) {
        bookService.deletePhieuDatTruoc(id);
        return ApiResponse.<String>builder()
                .result("deleted")
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_DAT_TRUOC')")
    @GetMapping("/{dauSachId}/reservations")
    public ApiResponse<List<PhieuDatTruocResponse>> getReservationsByDauSach(@PathVariable String dauSachId) {
        return ApiResponse.<List<PhieuDatTruocResponse>>builder()
                .result(bookService.getReservationsByDauSach(dauSachId))
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_DAT_TRUOC')")
    @PatchMapping("/reservations/{id}/confirm")
    public ApiResponse<PhieuDatTruocResponse> confirmReservation(@PathVariable String id) {
        return ApiResponse.<PhieuDatTruocResponse>builder()
                .result(bookService.confirmReservation(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PostMapping("/ebooks/purchase")
    public ApiResponse<PhieuMuaResponse> muaEBook(@RequestBody PhieuMuaRequest request) {
        return ApiResponse.<PhieuMuaResponse>builder()
                .result(bookService.muaEBook(request))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @GetMapping("/ebooks/my-purchases")
    public ApiResponse<List<PhieuMuaResponse>> getMyEBooks() {
        return ApiResponse.<List<PhieuMuaResponse>>builder()
                .result(bookService.getMyEBooks())
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_PHIEU_PHAT')")
    @PostMapping("/fines")
    public ApiResponse<PhieuPhatResponse> createPhieuPhat(@RequestBody PhieuPhatRequest request) {
        return ApiResponse.<PhieuPhatResponse>builder()
                .result(bookService.createPhieuPhat(request))
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_PHAT')")
    @GetMapping("/fines")
    public ApiResponse<List<PhieuPhatResponse>> getPhieuPhat() {
        return ApiResponse.<List<PhieuPhatResponse>>builder()
                .result(bookService.getPhieuPhat())
                .build();
    }

    @PreAuthorize("hasAuthority('GET_PHIEU_PHAT')")
    @GetMapping("/fines/{id}")
    public ApiResponse<PhieuPhatResponse> getPhieuPhatById(@PathVariable String id) {
        return ApiResponse.<PhieuPhatResponse>builder()
                .result(bookService.getPhieuPhatById(id))
                .build();
    }

    @PreAuthorize("hasRole('DOC_GIA')")
    @PatchMapping("/fines/{id}/pay")
    public ApiResponse<PhieuPhatResponse> payPhieuPhat(@PathVariable String id) {
        return ApiResponse.<PhieuPhatResponse>builder()
                .result(bookService.payPhieuPhat(id))
                .build();
    }
}

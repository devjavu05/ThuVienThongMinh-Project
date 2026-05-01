package com.devjavu.standardProject.controller;

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

    @Operation(summary = "Lay danh sach dau sach", security = {})
    @GetMapping
    public ApiResponse<List<DauSachResponse>> getDauSach() {
        return ApiResponse.<List<DauSachResponse>>builder()
                .result(bookService.getDauSach())
                .build();
    }

    @PreAuthorize("hasAuthority('CREATE_DAU_SACH')")
    @PostMapping
    public ApiResponse<DauSachResponse> createDauSach(@RequestBody DauSachCreationRequest request) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.createDauSach(request))
                .build();
    }

    @PreAuthorize("hasAuthority('UPDATE_DAU_SACH')")
    @PutMapping("{id}")
    public ApiResponse<DauSachResponse> updateDauSach(@RequestBody DauSachUpdateRequest request, @PathVariable String id) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.updateDauSach(request, id))
                .build();
    }

    @Operation(summary = "Lay chi tiet dau sach", security = {})
    @GetMapping("/{id}")
    public ApiResponse<DauSachResponse> getDauSachById(@PathVariable String id) {
        return ApiResponse.<DauSachResponse>builder()
                .result(bookService.getDauSachById(id))
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
    @GetMapping("/tickets/{id}")
    public ApiResponse<PhieuMuonResponse> getPhieuMuonById(@PathVariable String id) {
        return ApiResponse.<PhieuMuonResponse>builder()
                .result(bookService.getPhieuMuonById(id))
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
}

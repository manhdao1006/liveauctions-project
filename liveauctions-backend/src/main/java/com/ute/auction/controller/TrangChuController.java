package com.ute.auction.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.DanhMucDTO;
import com.ute.auction.dto.SanPhamDauGiaResponseDTO;
import com.ute.auction.service.IDanhMucService;
import com.ute.auction.service.ISanPhamDauGiaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trang-chu")
public class TrangChuController {

    private final IDanhMucService danhMucService;
    private final ISanPhamDauGiaService sanPhamDauGiaService;

    @GetMapping("/danh-muc")
    public ApiResponse<List<DanhMucDTO>> getNavigations() {
        return ApiResponse.<List<DanhMucDTO>>builder()
                .code(200)
                .message("Danh sách danh mục")
                .result(danhMucService.getNavigations())
                .build();
    }

    @GetMapping("/dau-gia-san-pham-upcoming")
    public ApiResponse<List<SanPhamDauGiaResponseDTO>> getSanPhamDauGiasUpcoming() {
        return ApiResponse.<List<SanPhamDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đấu giá")
                .result(sanPhamDauGiaService.getSanPhamDauGiasUpcoming())
                .build();
    }

    @GetMapping("/dau-gia-san-pham-trending")
    public ApiResponse<List<SanPhamDauGiaResponseDTO>> getSanPhamDauGiasTrending() {
        return ApiResponse.<List<SanPhamDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đấu giá")
                .result(sanPhamDauGiaService.getSanPhamDauGiasTrending())
                .build();
    }

    @GetMapping("/dau-gia-san-pham-kin")
    public ApiResponse<List<SanPhamDauGiaResponseDTO>> getSanPhamDauGiasKin() {
        return ApiResponse.<List<SanPhamDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đấu giá")
                .result(sanPhamDauGiaService.getSanPhamDauGiasKin())
                .build();
    }

    @GetMapping("/dau-gia-san-pham-online")
    public ApiResponse<List<SanPhamDauGiaResponseDTO>> getSanPhamDauGiasOnline() {
        return ApiResponse.<List<SanPhamDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đấu giá")
                .result(sanPhamDauGiaService.getSanPhamDauGiasOnline())
                .build();
    }

}

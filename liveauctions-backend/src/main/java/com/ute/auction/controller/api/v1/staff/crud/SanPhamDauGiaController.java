package com.ute.auction.controller.api.v1.staff.crud;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.service.ISanPhamDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPhamDauGia" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_STAFF + "/san-pham-dau-gia")
public class SanPhamDauGiaController {

    private final ISanPhamDauGiaService sanPhamDauGiaService;

    @GetMapping("/list")
    public ApiResponse<List<SanPhamDauGiaDTO>> getSanPhamDauGias() {
        return ApiResponse.<List<SanPhamDauGiaDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đấu giá")
                .result(sanPhamDauGiaService.getSanPhamDauGias())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<SanPhamDauGiaDTO> addSanPhamDauGia(@RequestBody SanPhamDauGiaDTO sanPhamDauGiaDTO) {
        return ApiResponse.<SanPhamDauGiaDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(sanPhamDauGiaService.addSanPhamDauGia(sanPhamDauGiaDTO))
                .build();
    }

    @PutMapping("edit/{maPhienDauGia}/{maSanPham}")
    public ApiResponse<SanPhamDauGiaDTO> updateSanPhamDauGia(@PathVariable("maPhienDauGia") long maPhienDauGia,
            @PathVariable("maSanPham") String maSanPham,
            @RequestBody SanPhamDauGiaDTO sanPhamDauGiaDTO) {
        return ApiResponse.<SanPhamDauGiaDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(sanPhamDauGiaService.updateSanPhamDauGia(maPhienDauGia, maSanPham, sanPhamDauGiaDTO))
                .build();
    }

}

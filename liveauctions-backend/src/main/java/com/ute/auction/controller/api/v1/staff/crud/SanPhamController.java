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
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.service.ISanPhamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPham" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_STAFF + "/san-pham")
public class SanPhamController {

    private final ISanPhamService sanPhamService;

    @GetMapping("/list")
    public ApiResponse<List<SanPhamDTO>> getSanPhams() {
        return ApiResponse.<List<SanPhamDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm")
                .result(sanPhamService.getSanPhams())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<SanPhamDTO> addSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        return ApiResponse.<SanPhamDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(sanPhamService.addSanPham(sanPhamDTO))
                .build();
    }

    @PutMapping("edit/{maSanPham}")
    public ApiResponse<SanPhamDTO> updateSanPham(@PathVariable("maSanPham") String maSanPham,
            @RequestBody SanPhamDTO sanPhamDTO) {
        return ApiResponse.<SanPhamDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(sanPhamService.updateSanPham(maSanPham, sanPhamDTO))
                .build();
    }

    @PutMapping("delete/{maSanPham}")
    public ApiResponse<String> deleteSanPham(@PathVariable("maSanPham") String maSanPham) {
        sanPhamService.deleteSanPham(maSanPham);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }

}

package com.ute.auction.controller.api.v1.staff;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.dto.SanPhamResponseDTO;
import com.ute.auction.service.ISanPhamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPham" + ApiName.API)
@RequestMapping(ApiUrl.API_STAFF + "/san-pham")
public class SanPhamController {

    private final ISanPhamService sanPhamService;

    @GetMapping("/list")
    public ApiResponse<List<SanPhamResponseDTO>> getSanPhams() {
        return ApiResponse.<List<SanPhamResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm")
                .result(sanPhamService.getSanPhams())
                .build();
    }

    @GetMapping("/maSanPham={maSanPham}")
    public ApiResponse<SanPhamResponseDTO> getSanPhamByMaSanPham(@PathVariable("maSanPham") String maSanPham) {
        return ApiResponse.<SanPhamResponseDTO>builder()
                .code(200)
                .message("Sản phẩm với mã sản phẩm " + maSanPham)
                .result(sanPhamService.getSanPhamByMaSanPham(maSanPham))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<SanPhamDTO> addSanPham(@ModelAttribute SanPhamDTO sanPhamDTO,
            @RequestParam(value = "anhSanPhamList", required = false) List<MultipartFile> anhSanPhamList)
            throws IOException {
        return ApiResponse.<SanPhamDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(sanPhamService.addSanPham(sanPhamDTO, anhSanPhamList))
                .build();
    }

    @PutMapping("edit/{maSanPham}")
    public ApiResponse<SanPhamDTO> updateSanPham(@PathVariable("maSanPham") String maSanPham,
            @ModelAttribute SanPhamDTO sanPhamDTO,
            @RequestParam(value = "anhSanPhamList", required = false) List<MultipartFile> anhSanPhamList,
            @RequestParam(value = "deletedImageNames", required = false) List<String> deletedImageNames)
            throws IOException {
        return ApiResponse.<SanPhamDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(sanPhamService.updateSanPham(maSanPham, sanPhamDTO, anhSanPhamList, deletedImageNames))
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

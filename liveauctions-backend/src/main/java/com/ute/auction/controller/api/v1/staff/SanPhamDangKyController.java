package com.ute.auction.controller.api.v1.staff;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDangKyResponseDTO;
import com.ute.auction.service.ISanPhamDangKyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPhamDangKy" + ApiName.API)
@RequestMapping(ApiUrl.API_STAFF + "/san-pham-dang-ky")
public class SanPhamDangKyController {

    private final ISanPhamDangKyService sanPhamDangKyService;

    @GetMapping("/list")
    public ApiResponse<PageResponse<SanPhamDangKyResponseDTO>> getSanPhamDangKys(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<SanPhamDangKyResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đăng ký")
                .result(sanPhamDangKyService.getSanPhamDangKys(page, size))
                .build();
    }

    @GetMapping("/{maNguoiBan}")
    public ApiResponse<SanPhamDangKyResponseDTO> getSanPhamDangKys(@PathVariable("maNguoiBan") long maNguoiBan) {
        return ApiResponse.<SanPhamDangKyResponseDTO>builder()
                .code(200)
                .message("Danh sách sản phẩm đăng ký")
                .result(sanPhamDangKyService.getSanPhamDangKyByMaSanPhamDangKy(maNguoiBan))
                .build();
    }

}

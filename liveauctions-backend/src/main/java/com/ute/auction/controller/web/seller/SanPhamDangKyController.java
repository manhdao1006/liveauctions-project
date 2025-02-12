package com.ute.auction.controller.web.seller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.dto.SanPhamDangKyResponseDTO;
import com.ute.auction.service.ISanPhamDangKyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPhamDangKy" + ApiName.WEB)
@RequestMapping(ApiUrl.API_SELLER + "/san-pham-dang-ky")
public class SanPhamDangKyController {

    private final ISanPhamDangKyService sanPhamDangKyService;

    @GetMapping("/nguoi-ban/{maNguoiBan}")
    public ApiResponse<PageResponse<SanPhamDangKyResponseDTO>> getSanPhamDangKysByMaNguoiBan(
            @PathVariable("maNguoiBan") long maNguoiBan,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<SanPhamDangKyResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm đăng ký")
                .result(sanPhamDangKyService.getSanPhamDangKysByMaNguoiBan(maNguoiBan, page, size))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<SanPhamDangKyDTO> registerProduct(
            @RequestBody SanPhamDangKyDTO sanPhamDangKyDTO) {
        return ApiResponse.<SanPhamDangKyDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(sanPhamDangKyService.registerSanPham(sanPhamDangKyDTO))
                .build();
    }

}

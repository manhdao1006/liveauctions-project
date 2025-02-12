package com.ute.auction.controller.web.seller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamResponseDTO;
import com.ute.auction.service.ISanPhamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "sanPham" + ApiName.WEB)
@RequestMapping(ApiUrl.API_SELLER + "/san-pham")
public class SanPhamController {

    private final ISanPhamService sanPhamService;

    @GetMapping("/nguoi-ban/{maNguoiBan}")
    public ApiResponse<PageResponse<SanPhamResponseDTO>> getProductsBySellerId(
            @PathVariable("maNguoiBan") long maNguoiBan,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<SanPhamResponseDTO>>builder()
                .code(200)
                .message("Danh sách sản phẩm")
                .result(sanPhamService.getSanPhamsByMaNguoiBan(maNguoiBan, page, size))
                .build();
    }

}

package com.ute.auction.controller.web.buyer.crud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.LichSuDauGiaResponseDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.service.ILichSuDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nguoiMua" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_BUYER)
public class LichSuDauGiaController {

    private final ILichSuDauGiaService lichSuDauGiaService;

    @GetMapping("/list/{maNguoiDung}")
    public ApiResponse<PageResponse<LichSuDauGiaResponseDTO>> getNguoiBans(
            @PathVariable("maNguoiDung") long maNguoiDung,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<LichSuDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách người bán")
                .result(lichSuDauGiaService.getLichSuDauGiasByMaNguoiMua(maNguoiDung, page, size))
                .build();
    }

}

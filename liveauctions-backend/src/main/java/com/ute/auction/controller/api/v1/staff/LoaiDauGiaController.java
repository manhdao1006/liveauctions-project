package com.ute.auction.controller.api.v1.staff;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.LoaiDauGiaDTO;
import com.ute.auction.service.ILoaiDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "loaiDauGia" + ApiName.API)
@RequestMapping(ApiUrl.API_STAFF + "/loai-dau-gia")
public class LoaiDauGiaController {

    private final ILoaiDauGiaService loaiDauGiaService;

    @GetMapping("/list")
    public ApiResponse<List<LoaiDauGiaDTO>> getLoaiDauGias() {
        return ApiResponse.<List<LoaiDauGiaDTO>>builder()
                .code(200)
                .message("Danh sách loại đấu giá")
                .result(loaiDauGiaService.getLoaiDauGias())
                .build();
    }

}

package com.ute.auction.controller.api.v1.staff;

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
import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.service.IPhienDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "phienDauGia" + ApiName.API)
@RequestMapping(ApiUrl.API_STAFF + "/phien-dau-gia")
public class PhienDauGiaController {

    private final IPhienDauGiaService phienDauGiaService;

    @GetMapping("/list")
    public ApiResponse<List<PhienDauGiaDTO>> getPhienDauGias() {
        return ApiResponse.<List<PhienDauGiaDTO>>builder()
                .code(200)
                .message("Danh sách phiên đấu giá")
                .result(phienDauGiaService.getPhienDauGias())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<PhienDauGiaDTO> addPhienDauGia(@RequestBody PhienDauGiaDTO phienDauGiaDTO) {
        return ApiResponse.<PhienDauGiaDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(phienDauGiaService.addPhienDauGia(phienDauGiaDTO))
                .build();
    }

    @PutMapping("edit/{maPhienDauGia}")
    public ApiResponse<PhienDauGiaDTO> updatePhienDauGia(@PathVariable("maPhienDauGia") long maPhienDauGia,
            @RequestBody PhienDauGiaDTO phienDauGiaDTO) {
        return ApiResponse.<PhienDauGiaDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(phienDauGiaService.updatePhienDauGia(maPhienDauGia, phienDauGiaDTO))
                .build();
    }

    @PutMapping("delete/{maPhienDauGia}")
    public ApiResponse<String> deletePhienDauGia(@PathVariable("maPhienDauGia") long maPhienDauGia) {
        phienDauGiaService.deletePhienDauGia(maPhienDauGia);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }

}

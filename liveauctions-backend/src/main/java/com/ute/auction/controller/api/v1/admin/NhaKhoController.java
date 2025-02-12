package com.ute.auction.controller.api.v1.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.NhaKhoDTO;
import com.ute.auction.service.INhaKhoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController(value = "nhaKho" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nha-kho")
public class NhaKhoController {

    private final INhaKhoService nhaKhoService;

    @GetMapping("/list")
    public ApiResponse<List<NhaKhoDTO>> getNhaKhos() {
        return ApiResponse.<List<NhaKhoDTO>>builder()
                .code(200)
                .message("Danh sách nhà kho")
                .result(nhaKhoService.getNhaKhos())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NhaKhoDTO> addNhaKho(@RequestBody NhaKhoDTO nhaKhoDTO) {
        return ApiResponse.<NhaKhoDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(nhaKhoService.addNhaKho(nhaKhoDTO))
                .build();
    }

    @PutMapping("edit/{maNhaKho}")
    public ApiResponse<NhaKhoDTO> updateNhaKho(@PathVariable("maNhaKho") long maNhaKho,
            @RequestBody NhaKhoDTO nhaKhoDTO) {
        return ApiResponse.<NhaKhoDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(nhaKhoService.updateNhaKho(maNhaKho, nhaKhoDTO))
                .build();
    }

    @PutMapping("delete/{maNhaKho}")
    public ApiResponse<String> deleteNhaKho(@PathVariable("maNhaKho") long maNhaKho) {
        nhaKhoService.deleteNhaKho(maNhaKho);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }

}

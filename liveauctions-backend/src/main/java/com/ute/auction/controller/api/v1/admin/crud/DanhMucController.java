package com.ute.auction.controller.api.v1.admin.crud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.service.IDanhMucService;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.DanhMucDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController(value = "danhMuc" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/danh-muc")
public class DanhMucController {

    private final IDanhMucService danhMucService;

    @GetMapping("/list")
    public ApiResponse<List<DanhMucDTO>> getDanhMucs() {
        return ApiResponse.<List<DanhMucDTO>>builder()
                .code(200)
                .message("Danh sách danh mục")
                .result(danhMucService.getDanhMucs())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<DanhMucDTO> addDanhMuc(@RequestBody DanhMucDTO danhMucDTO) {
        return ApiResponse.<DanhMucDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(danhMucService.addDanhMuc(danhMucDTO))
                .build();
    }

    @PutMapping("edit/{maDanhMuc}")
    public ApiResponse<DanhMucDTO> updateDanhMuc(@PathVariable("maDanhMuc") long maDanhMuc,
            @RequestBody DanhMucDTO danhMucDTO) {
        return ApiResponse.<DanhMucDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(danhMucService.updateDanhMuc(maDanhMuc, danhMucDTO))
                .build();
    }

    @DeleteMapping("/delete/{maDanhMuc}")
    public ApiResponse<?> deleteDanhMuc(@PathVariable("maDanhMuc") long maDanhMuc) {
        danhMucService.deleteDanhMuc(maDanhMuc);
        return ApiResponse.<DanhMucDTO>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }
}

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
import com.ute.auction.dto.DanhMucConDTO;
import com.ute.auction.service.IDanhMucConService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "danhMucCon" + ApiName.API)
@RequestMapping(ApiUrl.API_STAFF + "/danh-muc-con")
public class DanhMucConController {

    private final IDanhMucConService danhMucConService;

    @GetMapping("/list")
    public ApiResponse<List<DanhMucConDTO>> getDanhMucCons() {
        return ApiResponse.<List<DanhMucConDTO>>builder()
                .code(200)
                .message("Danh sách danh mục con")
                .result(danhMucConService.getDanhMucCons())
                .build();
    }

    @GetMapping("/maDanhMuc={maDanhMuc}")
    public ApiResponse<List<DanhMucConDTO>> getDanhMucConsByMaDanhMuc(@PathVariable("maDanhMuc") long maDanhMuc) {
        return ApiResponse.<List<DanhMucConDTO>>builder()
                .code(200)
                .message("Danh mục con với mã danh mục là " + maDanhMuc)
                .result(danhMucConService.getDanhMucConsByMaDanhMuc(maDanhMuc))
                .build();
    }

    @GetMapping("/maDanhMucCon={maDanhMucCon}")
    public ApiResponse<DanhMucConDTO> getDanhMucConByMaDanhMucCon(@PathVariable("maDanhMucCon") long maDanhMucCon) {
        return ApiResponse.<DanhMucConDTO>builder()
                .code(200)
                .message("Danh mục con với mã danh mục con là " + maDanhMucCon)
                .result(danhMucConService.getDanhMucConByMaDanhMucCon(maDanhMucCon))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<DanhMucConDTO> addDanhMucCon(@RequestBody DanhMucConDTO danhMucDTO) {
        return ApiResponse.<DanhMucConDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(danhMucConService.addDanhMucCon(danhMucDTO))
                .build();
    }

    @PutMapping("edit/{maDanhMucCon}")
    public ApiResponse<DanhMucConDTO> updateDanhMucCon(@PathVariable("maDanhMucCon") long maDanhMucCon,
            @RequestBody DanhMucConDTO danhMucDTO) {
        return ApiResponse.<DanhMucConDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(danhMucConService.updateDanhMucCon(maDanhMucCon, danhMucDTO))
                .build();
    }

    @PutMapping("/delete/{maDanhMucCon}")
    public ApiResponse<String> deleteDanhMucCon(@PathVariable("maDanhMucCon") long maDanhMucCon) {
        danhMucConService.deleteDanhMucCon(maDanhMucCon);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }

}

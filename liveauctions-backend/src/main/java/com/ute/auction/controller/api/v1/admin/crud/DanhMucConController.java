package com.ute.auction.controller.api.v1.admin.crud;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
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
@RestController(value = "danhMucCon" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/danh-muc-con")
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

    @DeleteMapping("/delete/{maDanhMucCon}")
    public ApiResponse<?> deleteDanhMucCon(@PathVariable("maDanhMucCon") long maDanhMucCon) {
        danhMucConService.deleteDanhMucCon(maDanhMucCon);
        return ApiResponse.<DanhMucConDTO>builder()
                .code(200)
                .message("Xóa thành công")
                .build();
    }

}

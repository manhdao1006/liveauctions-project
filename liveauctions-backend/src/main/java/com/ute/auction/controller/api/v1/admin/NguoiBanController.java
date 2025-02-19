package com.ute.auction.controller.api.v1.admin;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NguoiBanResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.service.INguoiBanService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nguoiBan" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nguoi-ban")
public class NguoiBanController {

    private final INguoiBanService nguoiBanService;

    @GetMapping("/list")
    public ApiResponse<PageResponse<NguoiBanResponseDTO>> getNguoiBans(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<NguoiBanResponseDTO>>builder()
                .code(200)
                .message("Danh sách người bán")
                .result(nguoiBanService.getNguoiBans(page, size))
                .build();
    }

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NguoiBanResponseDTO> getNguoiBanByMaNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NguoiBanResponseDTO>builder()
                .code(200)
                .message("Người bán với mã người bán là " + maNguoiDung)
                .result(nguoiBanService.getNguoiBanByMaNguoiBan(maNguoiDung))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NguoiBanResponseDTO> addNguoiBan(
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NguoiBanDTO nguoiBanDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NguoiBanResponseDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(nguoiBanService.addNguoiBan(nguoiDungDTO, nguoiBanDTO, file))
                .build();
    }

    @PutMapping("/edit/{maNguoiDung}")
    public ApiResponse<NguoiBanResponseDTO> updateNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung,
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NguoiBanDTO nguoiBanDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NguoiBanResponseDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(nguoiBanService.updateNguoiBan(maNguoiDung, nguoiDungDTO, nguoiBanDTO, file))
                .build();
    }

    @PutMapping("delete/{maNguoiDung}")
    public ApiResponse<String> deleteNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiBanService.deleteNguoiBan(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công!")
                .build();
    }

    @PutMapping("ban/{maNguoiDung}")
    public ApiResponse<String> banNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiBanService.banNguoiBan(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cấm thành công!")
                .build();
    }

}

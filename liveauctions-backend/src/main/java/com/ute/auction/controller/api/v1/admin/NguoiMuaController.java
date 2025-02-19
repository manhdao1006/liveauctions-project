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
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.NguoiMuaResponseDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.service.INguoiMuaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nguoiMua" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nguoi-mua")
public class NguoiMuaController {

    private final INguoiMuaService nguoiMuaService;

    @GetMapping("/list")
    public ApiResponse<PageResponse<NguoiMuaResponseDTO>> getNguoiMuas(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<NguoiMuaResponseDTO>>builder()
                .code(200)
                .message("Danh sách người mua")
                .result(nguoiMuaService.getNguoiMuas(page, size))
                .build();
    }

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NguoiMuaResponseDTO> getNguoiMuaByMaNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NguoiMuaResponseDTO>builder()
                .code(200)
                .message("Người mua với mã người mua là " + maNguoiDung)
                .result(nguoiMuaService.getNguoiMuaByMaNguoiMua(maNguoiDung))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NguoiMuaResponseDTO> addNguoiMua(
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NguoiMuaDTO nguoiMuaDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NguoiMuaResponseDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(nguoiMuaService.addNguoiMua(nguoiDungDTO, nguoiMuaDTO, file))
                .build();
    }

    @PutMapping("/edit/{maNguoiDung}")
    public ApiResponse<NguoiMuaResponseDTO> updateNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung,
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NguoiMuaDTO nguoiMuaDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NguoiMuaResponseDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(nguoiMuaService.updateNguoiMua(maNguoiDung, nguoiDungDTO, nguoiMuaDTO, file))
                .build();
    }

    @PutMapping("delete/{maNguoiDung}")
    public ApiResponse<String> deleteNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiMuaService.deleteNguoiMua(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công!")
                .build();
    }

    @PutMapping("ban/{maNguoiDung}")
    public ApiResponse<String> banNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiMuaService.banNguoiMua(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cấm thành công!")
                .build();
    }

}

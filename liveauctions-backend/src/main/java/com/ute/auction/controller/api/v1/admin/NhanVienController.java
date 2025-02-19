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
import com.ute.auction.dto.NhanVienDTO;
import com.ute.auction.dto.NhanVienResponseDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.service.INhanVienService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nhanVien" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nhan-vien")
public class NhanVienController {

    private final INhanVienService nhanVienService;

    @GetMapping("/list")
    public ApiResponse<PageResponse<NhanVienResponseDTO>> getNhanViens(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<NhanVienResponseDTO>>builder()
                .code(200)
                .message("Danh sách nhân viên")
                .result(nhanVienService.getNhanViens(page, size))
                .build();
    }

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NhanVienResponseDTO> getNhanVienByMaNhanVien(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NhanVienResponseDTO>builder()
                .code(200)
                .message("Nhân viên với mã nhân viên là " + maNguoiDung)
                .result(nhanVienService.getNhanVienByMaNhanVien(maNguoiDung))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NhanVienResponseDTO> addNhanVien(
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NhanVienDTO nhanVienDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhanVienResponseDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(nhanVienService.addNhanVien(nguoiDungDTO, nhanVienDTO, file))
                .build();
    }

    @PutMapping("/edit/{maNguoiDung}")
    public ApiResponse<NhanVienResponseDTO> updateNhanVien(@PathVariable("maNguoiDung") long maNguoiDung,
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NhanVienDTO nhanVienDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhanVienResponseDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(nhanVienService.updateNhanVien(maNguoiDung, nguoiDungDTO, nhanVienDTO, file))
                .build();
    }

    @PutMapping("delete/{maNguoiDung}")
    public ApiResponse<String> deleteNhanVien(@PathVariable("maNguoiDung") long maNguoiDung) {
        nhanVienService.deleteNhanVien(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công!")
                .build();
    }

    @PutMapping("ban/{maNguoiDung}")
    public ApiResponse<String> banNhanVien(@PathVariable("maNguoiDung") long maNguoiDung) {
        nhanVienService.banNhanVien(maNguoiDung);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cấm thành công!")
                .build();
    }

}

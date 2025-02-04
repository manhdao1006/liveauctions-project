package com.ute.auction.controller.api.v1.admin.crud;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.ute.auction.service.INhanVienService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nhanVien" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/nhan-vien")
public class NhanVienController {

    private final INhanVienService nhanVienService;

    @GetMapping("/list")
    public ApiResponse<List<NhanVienResponseDTO>> getNhanViens() {
        return ApiResponse.<List<NhanVienResponseDTO>>builder()
                .code(200)
                .message("Danh sách nhân viên")
                .result(nhanVienService.getNhanViens())
                .build();
    }

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NhanVienResponseDTO> getNhanViens(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NhanVienResponseDTO>builder()
                .code(200)
                .message("Nhân viên với mã nhân viên là " + maNguoiDung)
                .result(nhanVienService.getNhanVienByMaNhanVien(maNguoiDung))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NhanVienResponseDTO> addAppraiser(
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @ModelAttribute NhanVienDTO nhanVienDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhanVienResponseDTO>builder()
                .code(200)
                .message("Save successfully!")
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
    public ResponseEntity<String> deleteNhanVien(@PathVariable("maNguoiDung") long maNguoiDung) {
        nhanVienService.deleteNhanVien(maNguoiDung);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PutMapping("ban/{maNguoiDung}")
    public ResponseEntity<String> banNhanVien(@PathVariable("maNguoiDung") long maNguoiDung) {
        nhanVienService.banNhanVien(maNguoiDung);
        return ResponseEntity.ok("Cấm thành công");
    }

}

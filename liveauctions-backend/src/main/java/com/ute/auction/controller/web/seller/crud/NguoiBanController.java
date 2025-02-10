package com.ute.auction.controller.web.seller.crud;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.ute.auction.service.INguoiBanService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "user" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class NguoiBanController {

    private final INguoiBanService nguoiBanService;

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NguoiBanResponseDTO> getNguoiBanByMaNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NguoiBanResponseDTO>builder()
                .code(200)
                .message("Nhân viên với mã người bán là " + maNguoiDung)
                .result(nguoiBanService.getNguoiBanByMaNguoiBan(maNguoiDung))
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
    public ResponseEntity<String> deleteNguoiBan(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiBanService.deleteNguoiBan(maNguoiDung);
        return ResponseEntity.ok("Xóa thành công");
    }

}

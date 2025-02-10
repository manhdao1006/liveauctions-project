package com.ute.auction.controller.web.buyer.crud;

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
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.NguoiMuaResponseDTO;
import com.ute.auction.service.INguoiMuaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nguoiMua" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_BUYER)
public class NguoiMuaController {

    private final INguoiMuaService nguoiMuaService;

    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NguoiMuaResponseDTO> getNguoiMuaByMaNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NguoiMuaResponseDTO>builder()
                .code(200)
                .message("Nhân viên với mã người mua là " + maNguoiDung)
                .result(nguoiMuaService.getNguoiMuaByMaNguoiMua(maNguoiDung))
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
    public ResponseEntity<String> deleteNguoiMua(@PathVariable("maNguoiDung") long maNguoiDung) {
        nguoiMuaService.deleteNguoiMua(maNguoiDung);
        return ResponseEntity.ok("Xóa thành công");
    }

}

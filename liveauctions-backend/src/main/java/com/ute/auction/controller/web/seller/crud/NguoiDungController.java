package com.ute.auction.controller.web.seller.crud;

import java.io.IOException;

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
import com.ute.auction.service.INguoiDungService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "user" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class NguoiDungController {

    private final INguoiDungService nguoiDungService;

    // Build API update profile of seller
    @PutMapping("/update-profile/{maNguoiDung}")
    public ApiResponse<NguoiDungDTO> updateProfile(@PathVariable("maNguoiDung") long maNguoiDung,
            @ModelAttribute NguoiDungDTO nguoiDungDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Update successfully!")
                .result(nguoiDungService.updateProfile(maNguoiDung, nguoiDungDTO, file))
                .build();
    }

}

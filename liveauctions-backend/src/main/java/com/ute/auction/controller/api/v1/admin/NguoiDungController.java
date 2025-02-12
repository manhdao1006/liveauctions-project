package com.ute.auction.controller.api.v1.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.service.INguoiDungService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nguoiDung" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nguoi-dung")
public class NguoiDungController {

    private final INguoiDungService nguoiDungService;

    @GetMapping("/role/{maVaiTro}")
    public ResponseEntity<List<NguoiDungDTO>> getNguoiDungsByMaVaiTro(@PathVariable("maVaiTro") long maVaiTro) {
        List<NguoiDungDTO> nguoiDungs = nguoiDungService.getNguoiDungsByMaVaiTro(maVaiTro);
        return ResponseEntity.ok(nguoiDungs);
    }

}

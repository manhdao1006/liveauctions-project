package com.ute.auction.controller.api.v1.admin.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.NguoiMuaResponseDTO;
import com.ute.auction.service.INguoiMuaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "buyer" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/users")
public class NguoiMuaController {

    private final INguoiMuaService buyerService;

    // Build API get buyer by id
    @GetMapping("/buyer/id={buyerId}")
    public ApiResponse<NguoiMuaResponseDTO> getBuyerById(@PathVariable("buyerId") long id) {
        return ApiResponse.<NguoiMuaResponseDTO>builder()
                .code(200)
                .message("Nhân viên với mã người bán là " + id)
                .result(buyerService.getNguoiMuaByMaNguoiMua(id))
                .build();
    }

    // Build API get buyer by email
    @GetMapping("/buyer/email={email}")
    public ResponseEntity<NguoiMuaDTO> getBuyerByEmail(@PathVariable("email") String email) {
        NguoiMuaDTO buyerDTO = buyerService.getNguoiMuaByEmail(email);
        return ResponseEntity.ok(buyerDTO);
    }

}

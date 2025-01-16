package com.ute.auction.controller.api.v1.admin.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.service.INguoiMuaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "buyer" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/users")
public class NguoiMuaController {

    private final INguoiMuaService buyerService;

    // Build API get buyer by id
    @GetMapping("/buyer/id={buyerId}")
    public ResponseEntity<NguoiMuaDTO> getBuyerById(@PathVariable("buyerId") long id) {
        NguoiMuaDTO buyerDTO = buyerService.getNguoiMuaByMaNguoiMua(id);
        return ResponseEntity.ok(buyerDTO);
    }

    // Build API get buyer by email
    @GetMapping("/buyer/email={email}")
    public ResponseEntity<NguoiMuaDTO> getBuyerByEmail(@PathVariable("email") String email) {
        NguoiMuaDTO buyerDTO = buyerService.getNguoiMuaByEmail(email);
        return ResponseEntity.ok(buyerDTO);
    }

}

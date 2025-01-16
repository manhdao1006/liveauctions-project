package com.ute.auction.controller.web.seller.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.service.ISanPhamDangKyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "registrationProduct" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class SanPhamDangKyController {

        private final ISanPhamDangKyService registrationProductService;

        // Build API register product
        @PostMapping("/register-product")
        public ResponseEntity<SanPhamDangKyDTO> registerProduct(
                        @RequestBody SanPhamDangKyDTO registrationProductDTO) {
                SanPhamDangKyDTO savedRegistrationProduct = registrationProductService
                                .registerProduct(registrationProductDTO);
                return new ResponseEntity<>(savedRegistrationProduct, HttpStatus.OK);
        }

}

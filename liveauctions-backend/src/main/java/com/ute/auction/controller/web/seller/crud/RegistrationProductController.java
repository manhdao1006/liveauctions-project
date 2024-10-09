package com.ute.auction.controller.web.seller.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.RegistrationProductDTO;
import com.ute.auction.service.IRegistrationProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "registrationProduct" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class RegistrationProductController {

        private final IRegistrationProductService registrationProductService;

        // Build API register product
        @PostMapping("/register-product")
        public ResponseEntity<RegistrationProductDTO> registerProduct(
                        @RequestBody RegistrationProductDTO registrationProductDTO) {
                RegistrationProductDTO savedRegistrationProduct = registrationProductService
                                .registerProduct(registrationProductDTO);
                return new ResponseEntity<>(savedRegistrationProduct, HttpStatus.OK);
        }

}

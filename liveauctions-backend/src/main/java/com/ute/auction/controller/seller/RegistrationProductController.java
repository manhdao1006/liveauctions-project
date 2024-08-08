package com.ute.auction.controller.seller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.RegistrationProductDTO;
import com.ute.auction.service.IRegistrationProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class RegistrationProductController {

    private final IRegistrationProductService registrationProductService;

    // Build API get registration products by seller id
    @GetMapping("/registration-products/{id}")
    public ResponseEntity<List<RegistrationProductDTO>> getRegistrationProductsBySellerId(@PathVariable("id") Long sellerId, 
                                                                                        @RequestParam("page") int page, 
                                                                                        @RequestParam(defaultValue = "10") int size) {
        List<RegistrationProductDTO> registrationProducts = registrationProductService.getRegistrationProductsBySellerId(sellerId, page, size);
        return ResponseEntity.ok(registrationProducts);
    }

    // Build API register product
    @PostMapping("/register-product")
    public ResponseEntity<RegistrationProductDTO> registerProduct(@RequestBody RegistrationProductDTO registrationProductDTO) {
        RegistrationProductDTO savedRegistrationProduct = registrationProductService.registerProduct(registrationProductDTO);
        return new ResponseEntity<>(savedRegistrationProduct, HttpStatus.OK);
    }

}

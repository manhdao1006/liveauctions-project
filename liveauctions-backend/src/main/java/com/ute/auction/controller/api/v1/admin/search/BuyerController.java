package com.ute.auction.controller.api.v1.admin.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.BuyerDTO;
import com.ute.auction.service.IBuyerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "buyer" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/users")
public class BuyerController {

    private final IBuyerService buyerService;

    // Build API get buyer by id
    @GetMapping("/buyer/id={buyerId}")
    public ResponseEntity<BuyerDTO> getBuyerById(@PathVariable("buyerId") int id) {
        BuyerDTO buyerDTO = buyerService.getBuyerById(id);
        return ResponseEntity.ok(buyerDTO);
    }

    // Build API get buyer by email
    @GetMapping("/buyer/email={email}")
    public ResponseEntity<BuyerDTO> getBuyerByEmail(@PathVariable("email") String email) {
        BuyerDTO buyerDTO = buyerService.getBuyerByEmail(email);
        return ResponseEntity.ok(buyerDTO);
    }

}

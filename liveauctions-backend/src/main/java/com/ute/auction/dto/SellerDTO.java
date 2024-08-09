package com.ute.auction.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SellerDTO {

    private Long sellerId;
    private String taxCode;
    private LocalDate registrationDate;
    private String nameOnCard;
    private String cardNumber;
    private String issuingBank;
    private LocalDate expiration;
    private String cvv;
    private String billingAddress;
    private String delFlag;
    private UserDTO user;
    private List<SellerTaxDTO> sellerTaxes;
    private List<RegistrationProductDTO> registrationProducts;
    private List<ProductDTO> products;

}

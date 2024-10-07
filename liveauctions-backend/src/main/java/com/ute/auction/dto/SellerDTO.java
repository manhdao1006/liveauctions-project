package com.ute.auction.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

    private Integer sellerId;
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
    private List<TaxDTO> taxes;
    private List<RegistrationProductDTO> registrationProducts;
    private List<ProductDTO> products;

}

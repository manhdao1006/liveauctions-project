package com.ute.auction.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BuyerDTO {

    private Long buyerId;
    private LocalDate registrationDate;
    private String nameOnCard;
    private String cardNumber;
    private String issuingBank;
    private LocalDate expiration;
    private String cvv;
    private String billingAddress;
    private String delFlag;
    private UserDTO user;
    private List<DepositDTO> deposits;
    private List<AuctionHistoryDTO> auctionHistories;
    
}

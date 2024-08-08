package com.ute.auction.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AppraiserDTO {

    private Long id;
    private String name;
    private String email;
    private String gender;
    private String phoneNumber;
    private String address;
    private String type;
    private String status;
    private String avatar;
    private LocalDate dob;
    private String description;
    private String delFlag;
    private List<ProductDTO> products;
    
}

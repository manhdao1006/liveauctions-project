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
public class AppraiserDTO {

    private Integer appraiserId;
    private String appraiserName;
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

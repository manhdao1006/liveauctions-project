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
public class UserDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String status;
    private String avatar;
    private LocalDate dob;
    private String gender;
    private String delFlag;
    private List<RoleDTO> roles;
    private CityDTO city;
    private BuyerDTO buyer;
    private SellerDTO seller;
    private StaffDTO staff;

}

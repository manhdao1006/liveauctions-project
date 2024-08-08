package com.ute.auction.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
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
    private List<RoleDTO> roles;
    private CityDTO city;
    private BuyerDTO buyer;
    private SellerDTO seller;
    private StaffDTO staff;
    
}

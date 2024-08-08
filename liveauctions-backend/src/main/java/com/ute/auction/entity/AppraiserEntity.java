package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "appraiser")
public class AppraiserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email should be valid")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{10}", message = "Phone number must contain exactly 10 digits")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "del_flag")
    private String delFlag;

    @OneToMany(mappedBy = "appraiser")
    private List<ProductEntity> products = new ArrayList<>();
    
}

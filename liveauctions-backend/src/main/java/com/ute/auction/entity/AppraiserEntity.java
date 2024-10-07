package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appraisers")
public class AppraiserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appraiser_id")
    private Integer appraiserId;

    @Column(name = "appraiser_name")
    private String appraiserName;

    @Column(name = "email")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email should be valid")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @OneToMany(mappedBy = "appraiser")
    private List<ProductEntity> products = new ArrayList<>();

}

package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class StaffEntity {

    @Id
    private Long staffId;

    @Column(name = "position")
    private String position;

    @MapsId
    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "staff")
    private List<AuctionEntity> auctions = new ArrayList<>();
    
}

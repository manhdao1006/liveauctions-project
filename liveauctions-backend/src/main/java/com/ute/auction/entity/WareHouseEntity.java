package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "warehouse")
public class WareHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "address")
    private String address;

    @Column(name = "operational_status")
    private String operationalStatus;

    @Column(name = "slot_status")
    private String slotStatus;

    @Column(name = "operating_day")
    private String operatingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "wareHouse")
    private List<ProductEntity> products = new ArrayList<>();
    
}

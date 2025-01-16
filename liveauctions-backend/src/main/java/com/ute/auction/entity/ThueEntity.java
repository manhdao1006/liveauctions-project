package com.ute.auction.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thue")
public class ThueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maThue")
    private Long maThue;

    @Column(name = "tenThue")
    private String tenThue;

    @Column(name = "giaThue")
    private BigDecimal giaThue;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @ManyToMany(mappedBy = "thues")
    private List<NguoiBanEntity> nguoiBans = new ArrayList<>();

}

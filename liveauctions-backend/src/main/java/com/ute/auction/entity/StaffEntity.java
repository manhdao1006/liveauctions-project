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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
public class StaffEntity {

    @Id
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "position")
    private String position;

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @MapsId
    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "staff")
    private List<AuctionEntity> auctions = new ArrayList<>();

}

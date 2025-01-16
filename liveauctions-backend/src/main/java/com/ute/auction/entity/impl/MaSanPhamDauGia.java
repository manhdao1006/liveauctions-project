package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MaSanPhamDauGia implements Serializable {

    private Long maPhienDauGia;
    private String maSanPham;

}

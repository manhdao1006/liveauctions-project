package com.ute.auction.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDatCocDTO {

    private Long maPhienDauGia;
    private Long maNguoiMua;
    private BigDecimal phanTramDatCoc;

}

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

    private PhienDauGiaDTO phienDauGia;
    private NguoiMuaDTO nguoiMua;
    private BigDecimal phanTramDatCoc;

}

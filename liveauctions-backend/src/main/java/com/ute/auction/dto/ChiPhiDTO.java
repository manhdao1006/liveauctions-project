package com.ute.auction.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChiPhiDTO {

    private Long maChiPhi;
    private String tenChiPhi;
    private BigDecimal giaChiPhi;
    private String moTa;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}

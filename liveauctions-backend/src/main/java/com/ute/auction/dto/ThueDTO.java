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
public class ThueDTO {

    private Long maThue;
    private String tenThue;
    private BigDecimal giaThue;
    private String moTa;
    private List<NguoiBanDTO> nguoiBans;

}

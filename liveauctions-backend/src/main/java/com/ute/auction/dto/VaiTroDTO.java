package com.ute.auction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaiTroDTO {

    private Long maVaiTro;
    private String tenVaiTro;
    private List<NguoiDungDTO> nguoiDungs;

}

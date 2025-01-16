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
public class PhuongXaDTO {

    private Long maPhuongXa;
    private String tenPhuongXa;
    private Long maQuanHuyen;
    private List<NhaKhoDTO> nhaKhos;
    private List<NguoiDungDTO> nguoiDungs;

}

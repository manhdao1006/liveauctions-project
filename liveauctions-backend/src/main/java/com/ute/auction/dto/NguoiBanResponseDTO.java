package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiBanResponseDTO {
    private NguoiDungDTO nguoiDung;
    private NguoiBanDTO nguoiBan;
}

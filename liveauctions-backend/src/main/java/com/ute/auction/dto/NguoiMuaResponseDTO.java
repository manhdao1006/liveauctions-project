package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiMuaResponseDTO {
    private NguoiDungDTO nguoiDung;
    private NguoiMuaDTO nguoiMua;
}

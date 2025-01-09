package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnhSanPhamDTO {

    private Long maAnhSanPham;
    private String tenAnh;
    private SanPhamDTO sanPham;

}

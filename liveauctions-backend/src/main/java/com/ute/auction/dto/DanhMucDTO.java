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
public class DanhMucDTO {

    private Long maDanhMuc;
    private String tenDanhMuc;
    private String trangThaiXoa;
    private List<DanhMucConDTO> danhMucCons;

}

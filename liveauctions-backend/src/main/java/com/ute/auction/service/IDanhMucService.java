package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.DanhMucDTO;

public interface IDanhMucService {

    List<DanhMucDTO> getDanhMucs();

    DanhMucDTO addDanhMuc(DanhMucDTO danhMucDTO);

    DanhMucDTO updateDanhMuc(long maDanhMuc, DanhMucDTO updatedDanhMuc);

    void deleteDanhMuc(long maDanhMuc);

}

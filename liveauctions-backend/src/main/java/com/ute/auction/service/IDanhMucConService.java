package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.DanhMucConDTO;

public interface IDanhMucConService {

    List<DanhMucConDTO> getDanhMucCons();

    List<DanhMucConDTO> getDanhMucConsByMaDanhMuc(long maDanhMuc);

    DanhMucConDTO addDanhMucCon(DanhMucConDTO danhMucConDTO);

    DanhMucConDTO updateDanhMucCon(long maDanhMucCon, DanhMucConDTO updatedDanhMucCon);

    void deleteDanhMucCon(long maDanhMucCon);

    DanhMucConDTO getDanhMucConByMaDanhMucCon(long maDanhMucCon);

}

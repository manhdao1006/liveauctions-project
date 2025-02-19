package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.QuanHuyenDTO;

public interface IQuanHuyenService {

    List<QuanHuyenDTO> getQuanHuyens();

    QuanHuyenDTO getQuanHuyenByMaPhuongXa(long maPhuongXa);

}

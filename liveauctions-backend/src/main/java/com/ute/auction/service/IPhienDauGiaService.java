package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.PhienDauGiaDTO;

public interface IPhienDauGiaService {

    List<PhienDauGiaDTO> getPhienDauGias();

    PhienDauGiaDTO addPhienDauGia(PhienDauGiaDTO phienDauGiaDTO);

    PhienDauGiaDTO updatePhienDauGia(long maPhienDauGia, PhienDauGiaDTO updatedPhienDauGia);

    void deletePhienDauGia(long maPhienDauGia);

}

package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.NhaKhoDTO;

public interface INhaKhoService {

    List<NhaKhoDTO> getNhaKhos();

    NhaKhoDTO addNhaKho(NhaKhoDTO nhaKhoDTO);

    NhaKhoDTO updateNhaKho(long maNhaKho, NhaKhoDTO updatedNhaKho);

    void deleteNhaKho(long maNhaKho);

    NhaKhoDTO getNhaKhoByMaNhaKho(long maNhaKho);

}

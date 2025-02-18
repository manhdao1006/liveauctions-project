package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.PhuongXaDTO;

public interface IPhuongXaService {

    List<PhuongXaDTO> getPhuongXas();

    List<PhuongXaDTO> getPhuongXasByMaQuanHuyen(long maQuanHuyen);

}

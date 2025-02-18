package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.PhuongXaConverter;
import com.ute.auction.dto.PhuongXaDTO;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.service.IPhuongXaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhuongXaService implements IPhuongXaService {

    private final PhuongXaRepository phuongXaRepository;
    private final PhuongXaConverter phuongXaConverter;

    @Override
    public List<PhuongXaDTO> getPhuongXas() {
        List<PhuongXaEntity> entities = phuongXaRepository.findAll();
        return entities.stream().map(phuongXaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<PhuongXaDTO> getPhuongXasByMaQuanHuyen(long maQuanHuyen) {
        List<PhuongXaEntity> entities = phuongXaRepository.findPhuongXasByQuanHuyen_MaQuanHuyen(maQuanHuyen);
        return entities.stream().map(phuongXaConverter::toDTO).collect(Collectors.toList());
    }

}

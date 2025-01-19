package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.NhaKhoConverter;
import com.ute.auction.dto.NhaKhoDTO;
import com.ute.auction.entity.NhaKhoEntity;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NhaKhoRepository;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.service.INhaKhoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NhaKhoService implements INhaKhoService {

    private final NhaKhoConverter nhaKhoConverter;
    private final NhaKhoRepository nhaKhoRepository;
    private final PhuongXaRepository phuongXaRepository;

    @Override
    public List<NhaKhoDTO> getNhaKhos() {
        List<NhaKhoEntity> entities = nhaKhoRepository.findNhaKhosByTrangThaiXoa("1");
        return entities.stream().map(nhaKhoConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public NhaKhoDTO addNhaKho(NhaKhoDTO nhaKhoDTO) {
        PhuongXaEntity phuongXaEntity = phuongXaRepository.findOneByMaPhuongXa(nhaKhoDTO.getMaPhuongXa())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy phường xã nào với mã phường xã là " + nhaKhoDTO.getMaPhuongXa()));
        NhaKhoEntity nhaKhoEntity = nhaKhoConverter.toEntity(nhaKhoDTO);
        nhaKhoEntity.setPhuongXa(phuongXaEntity);
        return nhaKhoConverter.toDTO(nhaKhoRepository.save(nhaKhoEntity));
    }

    @Override
    public NhaKhoDTO updateNhaKho(long maNhaKho, NhaKhoDTO updatedNhaKho) {
        NhaKhoEntity nhaKhoEntity = nhaKhoRepository.findOneByMaNhaKho(maNhaKho)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà kho nào với mã nhà kho là " + maNhaKho));
        if (updatedNhaKho.getMaPhuongXa() != null) {
            PhuongXaEntity phuongXaEntity = phuongXaRepository.findOneByMaPhuongXa(updatedNhaKho.getMaPhuongXa())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy phường xã nào với mã phường xã là " + updatedNhaKho.getMaPhuongXa()));
            nhaKhoEntity.setPhuongXa(phuongXaEntity);
        }
        NhaKhoEntity nhaKhoUpdated = nhaKhoConverter.toEntity(updatedNhaKho, nhaKhoEntity);
        return nhaKhoConverter.toDTO(nhaKhoRepository.save(nhaKhoUpdated));
    }

    @Override
    public void deleteNhaKho(long maNhaKho) {
        NhaKhoEntity nhaKhoEntity = nhaKhoRepository.findOneByMaNhaKho(maNhaKho)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà kho nào với mã nhà kho là " + maNhaKho));
        nhaKhoEntity.setTrangThaiXoa("0");
        nhaKhoRepository.save(nhaKhoEntity);
    }

}

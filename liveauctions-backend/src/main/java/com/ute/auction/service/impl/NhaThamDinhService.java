package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.NhaThamDinhConverter;
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.entity.NhaThamDinhEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NhaThamDinhRepository;
import com.ute.auction.service.INhaThamDinhService;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NhaThamDinhService implements INhaThamDinhService {

    private final NhaThamDinhRepository appraiserRepository;
    private final NhaThamDinhConverter appraiserConverter;

    /*
     * get all appraisers
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.findAll(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }
        List<NhaThamDinhDTO> models = new ArrayList<>();
        for (NhaThamDinhEntity item : entities) {
            NhaThamDinhDTO appraiserDTO = appraiserConverter.toDTO(item);
            models.add(appraiserDTO);
        }

        return models;
    }

    /*
     * get an appraiser by id
     * 
     * @param id
     * 
     * @return appraiser
     */
    @Override
    public NhaThamDinhDTO getAppraiserById(long id) {
        NhaThamDinhEntity appraiserEntity = appraiserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " not found"));
        NhaThamDinhDTO appraiserDTO = appraiserConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * get an appraiser by email
     * 
     * @param email
     * 
     * @return appraiser
     */
    @Override
    public NhaThamDinhDTO getAppraiserByEmail(String email) {
        NhaThamDinhEntity appraiserEntity = appraiserRepository.findOneByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + email + " not found"));
        NhaThamDinhDTO appraiserDTO = appraiserConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * add an appraiser
     * 
     * @param appraiser
     * 
     * @return appraiser
     */
    @Override
    @Transactional
    public NhaThamDinhDTO addAppraiser(NhaThamDinhDTO appraiser) {
        try {
            NhaThamDinhEntity appraiserEntity = appraiserConverter.toEntity(appraiser);
            appraiserEntity = appraiserRepository.save(appraiserEntity);
            return appraiserConverter.toDTO(appraiserEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

    /*
     * edit an existed appraiser
     * 
     * @param id, updatedAppraiser
     * 
     * @return appraiserUpdated
     */
    @Override
    @Transactional
    public NhaThamDinhDTO updateAppraiser(long id, NhaThamDinhDTO updatedAppraiser) {
        NhaThamDinhEntity appraiserEntity = appraiserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        appraiserEntity.setHoVaTen(updatedAppraiser.getHoVaTen());
        appraiserEntity.setEmail(updatedAppraiser.getEmail());
        appraiserEntity.setGioiTinh(updatedAppraiser.getGioiTinh());
        appraiserEntity.setSoDienThoai(updatedAppraiser.getSoDienThoai());
        appraiserEntity.setDiaChi(updatedAppraiser.getDiaChi());
        appraiserEntity.setLoai(updatedAppraiser.getLoai());
        appraiserEntity.setTrangThaiHoatDong(updatedAppraiser.getTrangThaiHoatDong());
        appraiserEntity.setAvatar(updatedAppraiser.getAvatar());
        appraiserEntity.setNgaySinh(updatedAppraiser.getNgaySinh());
        appraiserEntity.setMoTa(updatedAppraiser.getMoTa());

        try {
            NhaThamDinhEntity appraiserUpdated = appraiserRepository.save(appraiserEntity);
            return appraiserConverter.toDTO(appraiserUpdated);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

    /*
     * delete an existed appraiser
     * 
     * @param id
     */
    @Override
    public void deleteAppraiser(long id) {
        NhaThamDinhEntity appraiserEntity = appraiserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        if (appraiserEntity != null) {
            appraiserRepository.deleteById(id);
        }
    }

    /*
     * ban an existed appraiser
     * 
     * @param id
     */
    @Override
    @Transactional
    public void banAppraiser(long id) {
        NhaThamDinhEntity appraiserEntity = appraiserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        appraiserEntity.setTrangThaiHoatDong("Inactive");
        appraiserRepository.save(appraiserEntity);
    }

    /*
     * search appraiser
     * 
     * @param keyword, page, size
     * 
     * @return appraiser
     */
    @Override
    public List<NhaThamDinhDTO> searchAppraiser(String keyword, int page, int size) {
        List<NhaThamDinhEntity> appraiserExists = appraiserRepository.existsAppraiser(keyword);
        if (appraiserExists.isEmpty()) {
            throw new ResourceNotFoundException("No appraisers with keyword: " +
                    keyword);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.searchAppraiser(keyword, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No appraisers with page: " + page);
        }

        return entities.stream()
                .map(appraiserConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser asc by name
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedAscByName(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.sortedAscByName(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(appraiserConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser desc by name
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedDescByName(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.sortedDescByName(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(appraiserConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser from young to old by dob
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedAscByDoB(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.sortedAscByDoB(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(appraiserConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser from old to young by dob
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedDescByDoB(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = appraiserRepository.sortedDescByDoB(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(appraiserConverter::toDTO)
                .collect(Collectors.toList());
    }

}

package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.AppraiserConverter;
import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.entity.AppraiserEntity;
import com.ute.auction.exception.DuplicateEmailException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.AppraiserRepository;
import com.ute.auction.service.IAppraiserService;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

/**
 * AppraiserService
 *
 * Version 1.0
 *
 * Date: 17-07-2024
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * --------------------------------------------------------
 * 17-07-2024           ManhDao         Create
 */
@Service
@RequiredArgsConstructor
public class AppraiserService implements IAppraiserService {

    private final AppraiserRepository appraiserRepository;
    private final AppraiserConverter appraiserConverter;

    /*
     * get all appraisers
     * @param page, size
     * @return appraisers
     */
    @Override
    public List<AppraiserDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AppraiserEntity> entities = appraiserRepository.findAll(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }
        List<AppraiserDTO> models = new ArrayList<>();
        for (AppraiserEntity item: entities) {
            AppraiserDTO appraiserDTO = appraiserConverter.toDTO(item);
            models.add(appraiserDTO);
        }

        return models;
    }

    /*
     * get an appraiser by id
     * @param id
     * @return appraiser
     */
    @Override
    public AppraiserDTO getAppraiserById(Long id) {
        AppraiserEntity appraiserEntity = appraiserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " not found"));
        AppraiserDTO appraiserDTO = appraiserConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * get an appraiser by email
     * @param email
     * @return appraiser
     */
    @Override
    public AppraiserDTO getAppraiserByEmail(String email) {
        AppraiserEntity appraiserEntity = appraiserRepository.findOneByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + email + " not found"));
        AppraiserDTO appraiserDTO = appraiserConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * add an appraiser
     * @param appraiser
     * @return appraiser
     */
    @Override
    @Transactional
    public AppraiserDTO addAppraiser(AppraiserDTO appraiser) {
        try {
            AppraiserEntity appraiserEntity = appraiserConverter.toEntity(appraiser);
            appraiserEntity = appraiserRepository.save(appraiserEntity);
            return appraiserConverter.toDTO(appraiserEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEmailException("Email already exists!");
        }        
    }

    /*
     * edit an existed appraiser
     * @param id, updatedAppraiser
     * @return appraiserUpdated
     */
    @Override
    @Transactional
    public AppraiserDTO updateAppraiser(Long id, AppraiserDTO updatedAppraiser) {
        AppraiserEntity appraiserEntity = appraiserRepository.findById(id)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        appraiserEntity.setName(updatedAppraiser.getName());
        appraiserEntity.setEmail(updatedAppraiser.getEmail());
        appraiserEntity.setGender(updatedAppraiser.getGender());
        appraiserEntity.setPhoneNumber(updatedAppraiser.getPhoneNumber());
        appraiserEntity.setAddress(updatedAppraiser.getAddress());
        appraiserEntity.setType(updatedAppraiser.getType());
        appraiserEntity.setStatus(updatedAppraiser.getStatus());
        appraiserEntity.setAvatar(updatedAppraiser.getAvatar() != null ? Base64.getDecoder().decode(updatedAppraiser.getAvatar()) : null);
        appraiserEntity.setDob(updatedAppraiser.getDob());
        appraiserEntity.setDescription(updatedAppraiser.getDescription());
        
        try {
            AppraiserEntity appraiserUpdated = appraiserRepository.save(appraiserEntity);
            return appraiserConverter.toDTO(appraiserUpdated);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new DuplicateEmailException("Email already exists!");
        }
    }

    /*
     * delete an existed appraiser
     * @param id
     */
    @Override
    public void deleteAppraiser(Long id) {
        AppraiserEntity appraiserEntity = appraiserRepository.findById(id)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        if (appraiserEntity != null) {
            appraiserRepository.deleteById(id);
        }
    }

    /*
     * ban an existed appraiser
     * @param id
     */
    @Override
    @Transactional
    public void banAppraiser(Long id) {
        AppraiserEntity appraiserEntity = appraiserRepository.findById(id)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        appraiserEntity.setStatus("Inactive");
        appraiserRepository.save(appraiserEntity);
    }

    @Override
    public List<AppraiserDTO> getAllTest() {
        List<AppraiserEntity> entities = appraiserRepository.getAllTest();
        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No appraisers!");
        }
        List<AppraiserDTO> models = new ArrayList<>();
        for (AppraiserEntity item: entities) {
            AppraiserDTO appraiserDTO = appraiserConverter.toDTO(item);
            models.add(appraiserDTO);
        }

        return models;
    }

}

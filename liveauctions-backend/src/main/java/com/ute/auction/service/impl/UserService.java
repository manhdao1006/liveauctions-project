package com.ute.auction.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.UserConverter;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.UserEntity;
import com.ute.auction.repository.UserRepository;
import com.ute.auction.service.IUserService;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.CityRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * UserService
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
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final UserConverter userConverter;
    
    /*
     * get seller by id
     * @param id
     * @return seller
     */
    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findUserById(id);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        UserDTO userDTO = userConverter.toDTO(userEntity);
        return userDTO;
    }

    /*
     * get seller by email
     * @param email
     * @return seller
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        UserDTO userDTO = userConverter.toDTO(userEntity);
        return userDTO;
    }

    /*
     * edit profile of seller by id
     * @param id, updatedUser
     * @return userUpdated
     */
    @Override
    @Transactional
    public UserDTO updateProfile(Long id, UserDTO updatedUser) {
        UserEntity userEntity = userRepository.findUserById(id);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }
        userEntity.setFirstName(updatedUser.getFirstName());
        userEntity.setLastName(updatedUser.getLastName());
        userEntity.setEmail(updatedUser.getEmail());
        userEntity.setPassword(updatedUser.getPassword());
        userEntity.setPhoneNumber(updatedUser.getPhoneNumber());
        userEntity.setAddress(updatedUser.getAddress());
        userEntity.setDob(updatedUser.getDob());
        userEntity.setGender(updatedUser.getGender());

        if (updatedUser.getCity() != null && updatedUser.getCity().getId() != null) {
            CityEntity cityEntity = cityRepository.findById(updatedUser.getCity().getId())
                                                .orElseThrow(() -> new ResourceNotFoundException("City with id " + updatedUser.getCity().getId() + " is not found"));
            userEntity.setCity(cityEntity);
        }

        if (updatedUser.getAvatar() != null) {
            byte[] avatarBytes = Base64.getDecoder().decode(updatedUser.getAvatar());
            userEntity.setAvatar(avatarBytes);
        } else {
            userEntity.setAvatar(null);
        }

        UserEntity userUpdated = userRepository.save(userEntity);
        return userConverter.toDTO(userUpdated);
    }

    /*
     * forgot password
     * @param email, password
     */
    @Override
    @Transactional
    public void forgotPassword(String email, String password) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

}

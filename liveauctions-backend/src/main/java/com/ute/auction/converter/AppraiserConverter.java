package com.ute.auction.converter;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.entity.AppraiserEntity;

@Component
public class AppraiserConverter {

    public AppraiserDTO toDTO(AppraiserEntity entity) {
        AppraiserDTO appraiserDTO = new AppraiserDTO();
        appraiserDTO.setId(entity.getId());
        appraiserDTO.setName(entity.getName());
        appraiserDTO.setEmail(entity.getEmail());
        if (entity.getGender().contains("F")) {
            appraiserDTO.setGender("Female");
        } else if (entity.getGender().contains("M")) {
            appraiserDTO.setGender("Male");
        } else if (entity.getGender().contains("L")) {
            appraiserDTO.setGender("Other");
        }
        appraiserDTO.setPhoneNumber(entity.getPhoneNumber());
        appraiserDTO.setAddress(entity.getAddress());
        appraiserDTO.setType(entity.getType());
        appraiserDTO.setAvatar(entity.getAvatar() != null ? Base64.getEncoder().encodeToString(entity.getAvatar()) : null);
        appraiserDTO.setStatus(entity.getStatus());
        appraiserDTO.setDob(entity.getDob());
        appraiserDTO.setDescription(entity.getDescription());
        appraiserDTO.setDelFlag(entity.getDelFlag());

        return appraiserDTO;
    }

    public AppraiserEntity toEntity(AppraiserDTO dto) {
        AppraiserEntity appraiserEntity = new AppraiserEntity();
        appraiserEntity.setId(dto.getId());
        appraiserEntity.setName(dto.getName());
        appraiserEntity.setEmail(dto.getEmail());
        if (dto.getGender() == null || dto.getGender().toUpperCase().equals("Male".toUpperCase())) {
            appraiserEntity.setGender("M");
        } else if (dto.getGender().toUpperCase().equals("Female".toUpperCase())) {
            appraiserEntity.setGender("F");
        } else if (dto.getGender().toUpperCase().equals("Other".toUpperCase())) {
            appraiserEntity.setGender("L");
        }
        appraiserEntity.setPhoneNumber(dto.getPhoneNumber());
        appraiserEntity.setAddress(dto.getAddress());
        appraiserEntity.setType(dto.getType() == null ? "Internal" : dto.getType());
        appraiserEntity.setAvatar(dto.getAvatar() != null ? Base64.getDecoder().decode(dto.getAvatar()) : null);
        appraiserEntity.setStatus(dto.getStatus() == null ? "Active" : dto.getStatus());
        appraiserEntity.setDob(dto.getDob());
        appraiserEntity.setDescription(dto.getDescription());
        appraiserEntity.setDelFlag("1");

        return appraiserEntity;
    }

}

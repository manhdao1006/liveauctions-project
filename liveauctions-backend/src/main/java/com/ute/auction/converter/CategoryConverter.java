package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.CategoryDTO;
import com.ute.auction.entity.CategoryEntity;

@Component
public class CategoryConverter {

    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(entity.getId());
        categoryDTO.setName(entity.getName());

        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(dto.getId());
        categoryEntity.setName(dto.getName());

        return categoryEntity;
    }
    
}

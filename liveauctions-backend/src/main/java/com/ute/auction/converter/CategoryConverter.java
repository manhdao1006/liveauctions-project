package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.CategoryDTO;
import com.ute.auction.entity.CategoryEntity;

@Component
public class CategoryConverter {

    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(entity.getCategoryId());
        categoryDTO.setCategoryName(entity.getCategoryName());

        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(dto.getCategoryId());
        categoryEntity.setCategoryName(dto.getCategoryName());

        return categoryEntity;
    }

}

package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CategoryDTO;
import com.ute.auction.dto.SubCategoryDTO;
import com.ute.auction.entity.CategoryEntity;
import com.ute.auction.entity.SubCategoryEntity;

@Component
public class SubCategoryConverter {

    private CategoryConverter categoryConverter;

    @Autowired
    public void setCategoryConverter(@Lazy CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public SubCategoryDTO toDTO(SubCategoryEntity entity) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setId(entity.getId());
        subCategoryDTO.setName(entity.getName());
        subCategoryDTO.setCategory(toCategoryDTO(entity.getCategory()));

        return subCategoryDTO;
    }

    public SubCategoryEntity toEntity(SubCategoryDTO dto) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setId(dto.getId());
        subCategoryEntity.setName(dto.getName());
        subCategoryEntity.setCategory(toCategoryEntity(dto.getCategory()));

        return subCategoryEntity;
    }

    private CategoryEntity toCategoryEntity(CategoryDTO categoryDTO) {
        return categoryConverter.toEntity(categoryDTO);
    }

    private CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        return categoryConverter.toDTO(categoryEntity);
    }
    
}

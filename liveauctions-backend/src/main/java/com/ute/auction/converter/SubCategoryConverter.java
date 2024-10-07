package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CategoryDTO;
import com.ute.auction.dto.SubCategoryDTO;
import com.ute.auction.entity.CategoryEntity;
import com.ute.auction.entity.SubCategoryEntity;

@Component
public class SubCategoryConverter {

    private final CategoryConverter categoryConverter;

    public SubCategoryConverter(@Lazy CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public SubCategoryDTO toDTO(SubCategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSubCategoryId(entity.getSubCategoryId());
        subCategoryDTO.setSubCategoryName(entity.getSubCategoryName());
        subCategoryDTO.setCategory(toCategoryDTO(entity.getCategory()));

        return subCategoryDTO;
    }

    public SubCategoryEntity toEntity(SubCategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setSubCategoryId(dto.getSubCategoryId());
        subCategoryEntity.setSubCategoryName(dto.getSubCategoryName());
        subCategoryEntity.setCategory(toCategoryEntity(dto.getCategory()));

        return subCategoryEntity;
    }

    private CategoryEntity toCategoryEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return categoryConverter.toEntity(categoryDTO);
    }

    private CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return categoryConverter.toDTO(categoryEntity);
    }

}

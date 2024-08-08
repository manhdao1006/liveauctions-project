package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private List<SubCategoryDTO> subCategories;
    
}

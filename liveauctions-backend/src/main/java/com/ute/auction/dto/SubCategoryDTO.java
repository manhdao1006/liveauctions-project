package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubCategoryDTO {

    private Long id;
    private String name;
    private CategoryDTO category;
    private List<RegistrationProductDTO> registrationProducts;
    private List<ProductDTO> products;
    
}

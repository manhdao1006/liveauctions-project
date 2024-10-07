package com.ute.auction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDTO {

    private Integer subCategoryId;
    private String subCategoryName;
    private CategoryDTO category;
    private List<RegistrationProductDTO> registrationProducts;
    private List<ProductDTO> products;

}

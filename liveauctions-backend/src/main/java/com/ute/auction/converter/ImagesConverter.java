package com.ute.auction.converter;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.ImagesDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.entity.ImagesEntity;
import com.ute.auction.entity.ProductEntity;

@Component
public class ImagesConverter {

    private ProductConverter productConverter;

    @Autowired
    public void setProductConverter(@Lazy ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public ImagesDTO toDTO(ImagesEntity entity) {
        ImagesDTO imagesDTO = new ImagesDTO();
        imagesDTO.setId(entity.getId());
        imagesDTO.setName(entity.getName() != null ? Base64.getEncoder().encodeToString(entity.getName()) : null);
        imagesDTO.setProduct(toProductDTO(entity.getProduct()));

        return imagesDTO;
    }
    
    public ImagesEntity toEntity(ImagesDTO dto) {
        ImagesEntity imagesEntity = new ImagesEntity();
        imagesEntity.setId(dto.getId());
        imagesEntity.setName(dto.getName() != null ? Base64.getDecoder().decode(dto.getName()) : null);
        imagesEntity.setProduct(toProductEntity(dto.getProduct()));

        return imagesEntity;
    }

    public List<ImagesDTO> toDTOs(List<ImagesEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ImagesEntity> toEntities(List<ImagesDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    private ProductDTO toProductDTO(ProductEntity productEntity) {
        return productConverter.toDTO(productEntity);
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        return productConverter.toEntity(productDTO);
    }
    
}

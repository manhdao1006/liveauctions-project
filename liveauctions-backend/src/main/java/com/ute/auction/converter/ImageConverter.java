package com.ute.auction.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.ImageDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.entity.ImageEntity;
import com.ute.auction.entity.ProductEntity;

@Component
public class ImageConverter {

    private final ProductConverter productConverter;

    public ImageConverter(@Lazy ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public ImageDTO toDTO(ImageEntity entity) {
        if (entity == null) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(entity.getImageId());
        imageDTO.setImageName(entity.getImageName());
        imageDTO.setProduct(toProductDTO(entity.getProduct()));

        return imageDTO;
    }

    public ImageEntity toEntity(ImageDTO dto) {
        if (dto == null) {
            return null;
        }

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageId(dto.getImageId());
        imageEntity.setImageName(dto.getImageName());
        imageEntity.setProduct(toProductEntity(dto.getProduct()));

        return imageEntity;
    }

    public List<ImageDTO> toDTOs(List<ImageEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ImageEntity> toEntities(List<ImageDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    private ProductDTO toProductDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return productConverter.toDTO(productEntity);
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return productConverter.toEntity(productDTO);
    }

}

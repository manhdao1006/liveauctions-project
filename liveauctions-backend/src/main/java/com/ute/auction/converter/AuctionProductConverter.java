package com.ute.auction.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionDTO;
import com.ute.auction.dto.AuctionProductDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.entity.AuctionEntity;
import com.ute.auction.entity.AuctionProductEntity;
import com.ute.auction.entity.ProductEntity;

@Component
public class AuctionProductConverter {

    private final AuctionConverter auctionConverter;
    private final ProductConverter productConverter;

    public AuctionProductConverter(@Lazy AuctionConverter auctionConverter, @Lazy ProductConverter productConverter) {
        this.auctionConverter = auctionConverter;
        this.productConverter = productConverter;
    }

    public AuctionProductDTO toDTO(AuctionProductEntity entity) {
        if (entity == null) {
            return null;
        }

        AuctionProductDTO auctionProductDTO = new AuctionProductDTO();
        auctionProductDTO.setAuction(toAuctionDTO(entity.getAuction()));
        auctionProductDTO.setProduct(toProductDTO(entity.getProduct()));
        auctionProductDTO.setSlot(entity.getSlot());
        auctionProductDTO.setStatus(entity.getStatus());

        return auctionProductDTO;
    }

    public AuctionProductEntity toEntity(AuctionProductDTO dto) {
        if (dto == null) {
            return null;
        }

        AuctionProductEntity auctionProductEntity = new AuctionProductEntity();
        auctionProductEntity.setAuction(toAuctionEntity(dto.getAuction()));
        auctionProductEntity.setProduct(toProductEntity(dto.getProduct()));
        auctionProductEntity.setSlot(dto.getSlot());
        auctionProductEntity.setStatus(dto.getStatus());

        return auctionProductEntity;
    }

    public List<AuctionProductDTO> toDTOs(List<AuctionProductEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public List<AuctionProductEntity> toEntities(List<AuctionProductDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return productConverter.toEntity(productDTO);
    }

    private AuctionEntity toAuctionEntity(AuctionDTO auctionDTO) {
        if (auctionDTO == null) {
            return null;
        }
        return auctionConverter.toEntity(auctionDTO);
    }

    private ProductDTO toProductDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return productConverter.toDTO(productEntity);
    }

    private AuctionDTO toAuctionDTO(AuctionEntity auctionEntity) {
        if (auctionEntity == null) {
            return null;
        }
        return auctionConverter.toDTO(auctionEntity);
    }

}

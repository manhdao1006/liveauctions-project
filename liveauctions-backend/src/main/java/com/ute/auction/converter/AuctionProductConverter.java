package com.ute.auction.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    private AuctionConverter auctionConverter;
    private ProductConverter productConverter;
    
    @Autowired
    public void setAuctionConverter(@Lazy AuctionConverter auctionConverter) {
        this.auctionConverter = auctionConverter;
    }

    @Autowired
    public void setProductConverter(@Lazy ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public AuctionProductDTO toDTO(AuctionProductEntity entity) {
        AuctionProductDTO auctionProductDTO = new AuctionProductDTO();
        auctionProductDTO.setAuction(toAuctionDTO(entity.getAuction()));
        auctionProductDTO.setProduct(toProductDTO(entity.getProduct()));
        auctionProductDTO.setSlots(entity.getSlots());
        auctionProductDTO.setStatus(entity.getStatus());

        return auctionProductDTO;
    }

    public AuctionProductEntity toEntity(AuctionProductDTO dto) {
        AuctionProductEntity auctionProductEntity = new AuctionProductEntity();
        auctionProductEntity.setAuction(toAuctionEntity(dto.getAuction()));
        auctionProductEntity.setProduct(toProductEntity(dto.getProduct()));
        auctionProductEntity.setSlots(dto.getSlots());
        auctionProductEntity.setStatus(dto.getStatus());

        return auctionProductEntity;
    }

    public List<AuctionProductDTO> toDTOs(List<AuctionProductEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AuctionProductEntity> toEntities(List<AuctionProductDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        return productConverter.toEntity(productDTO);
    }

    private AuctionEntity toAuctionEntity(AuctionDTO auctionDTO) {
        return auctionConverter.toEntity(auctionDTO);
    }

    private ProductDTO toProductDTO(ProductEntity productEntity) {
        return productConverter.toDTO(productEntity);
    }

    private AuctionDTO toAuctionDTO(AuctionEntity auctionEntity) {
        return auctionConverter.toDTO(auctionEntity);
    }
    
}

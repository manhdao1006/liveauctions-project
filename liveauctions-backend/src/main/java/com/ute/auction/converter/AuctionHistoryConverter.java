package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionDTO;
import com.ute.auction.dto.AuctionHistoryDTO;
import com.ute.auction.dto.BuyerDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.entity.AuctionEntity;
import com.ute.auction.entity.AuctionHistoryEntity;
import com.ute.auction.entity.BuyerEntity;
import com.ute.auction.entity.ProductEntity;

@Component
public class AuctionHistoryConverter {

    private final ProductConverter productConverter;
    private final AuctionConverter auctionConverter;
    private final BuyerConverter buyerConverter;

    public AuctionHistoryConverter(@Lazy ProductConverter productConverter, @Lazy AuctionConverter auctionConverter,
            @Lazy BuyerConverter buyerConverter) {
        this.productConverter = productConverter;
        this.auctionConverter = auctionConverter;
        this.buyerConverter = buyerConverter;
    }

    public AuctionHistoryDTO toDTO(AuctionHistoryEntity entity) {
        if (entity == null) {
            return null;
        }

        AuctionHistoryDTO auctionHistoryDTO = new AuctionHistoryDTO();
        auctionHistoryDTO.setAuction(toAuctionDTO(entity.getAuction()));
        auctionHistoryDTO.setProduct(toProductDTO(entity.getProduct()));
        auctionHistoryDTO.setBuyer(toBuyerDTO(entity.getBuyer()));
        auctionHistoryDTO.setAuctionedPrice(entity.getAuctionedPrice());
        auctionHistoryDTO.setOrderDate(entity.getOrderDate());
        auctionHistoryDTO.setOrderStatus(entity.getOrderStatus());

        return auctionHistoryDTO;
    }

    public AuctionHistoryEntity toEntity(AuctionHistoryDTO dto) {
        if (dto == null) {
            return null;
        }

        AuctionHistoryEntity auctionHistoryEntity = new AuctionHistoryEntity();
        auctionHistoryEntity.setAuction(toAuctionEntity(dto.getAuction()));
        auctionHistoryEntity.setProduct(toProductEntity(dto.getProduct()));
        auctionHistoryEntity.setBuyer(toBuyerEntity(dto.getBuyer()));
        auctionHistoryEntity.setAuctionedPrice(dto.getAuctionedPrice());
        auctionHistoryEntity.setOrderDate(dto.getOrderDate());
        auctionHistoryEntity.setOrderStatus(dto.getOrderStatus());

        return auctionHistoryEntity;
    }

    private BuyerDTO toBuyerDTO(BuyerEntity buyerEntity) {
        if (buyerEntity == null) {
            return null;
        }
        return buyerConverter.toDTO(buyerEntity);
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

    private BuyerEntity toBuyerEntity(BuyerDTO buyerDTO) {
        if (buyerDTO == null) {
            return null;
        }
        return buyerConverter.toEntity(buyerDTO);
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

}

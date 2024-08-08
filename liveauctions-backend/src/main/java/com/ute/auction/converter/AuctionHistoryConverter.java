package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
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

    private ProductConverter productConverter;
    private AuctionConverter auctionConverter;
    private BuyerConverter buyerConverter;

    @Autowired
    public void setProductConverter(@Lazy ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setAuctionConverter(@Lazy AuctionConverter auctionConverter) {
        this.auctionConverter = auctionConverter;
    }

    @Autowired
    public void setBuyerConverter(@Lazy BuyerConverter buyerConverter) {
        this.buyerConverter = buyerConverter;
    }

    public AuctionHistoryDTO toDTO(AuctionHistoryEntity entity) {
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
        return buyerConverter.toDTO(buyerEntity);
    }

    private ProductDTO toProductDTO(ProductEntity productEntity) {
        return productConverter.toDTO(productEntity);
    }

    private AuctionDTO toAuctionDTO(AuctionEntity auctionEntity) {
        return auctionConverter.toDTO(auctionEntity);
    }

    private BuyerEntity toBuyerEntity(BuyerDTO buyerDTO) {
        return buyerConverter.toEntity(buyerDTO);
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        return productConverter.toEntity(productDTO);
    }

    private AuctionEntity toAuctionEntity(AuctionDTO auctionDTO) {
        return auctionConverter.toEntity(auctionDTO);
    }
    
}

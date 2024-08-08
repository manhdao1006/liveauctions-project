package com.ute.auction.converter;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.dto.RegistrationProductDTO;
import com.ute.auction.dto.SellerDTO;
import com.ute.auction.dto.SubCategoryDTO;
import com.ute.auction.entity.AuctionFormatEntity;
import com.ute.auction.entity.RegistrationProductEntity;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.SubCategoryEntity;

@Component
public class RegistrationProductConverter {

    private SellerConverter sellerConverter;
    private SubCategoryConverter subCategoryConverter;
    private AuctionFormatConverter auctionFormatConverter;

    @Autowired
    public void setSellerConverter(@Lazy SellerConverter sellerConverter) {
        this.sellerConverter = sellerConverter;
    }

    @Autowired
    public void setSubCategoryConverter(@Lazy SubCategoryConverter subCategoryConverter) {
        this.subCategoryConverter = subCategoryConverter;
    }

    @Autowired
    public void setAuctionFormatConverter(@Lazy AuctionFormatConverter auctionFormatConverter) {
        this.auctionFormatConverter = auctionFormatConverter;
    }
    
    public RegistrationProductDTO toDTO(RegistrationProductEntity entity) {
        RegistrationProductDTO registrationProductDTO = new RegistrationProductDTO();
        registrationProductDTO.setId(entity.getId());
        registrationProductDTO.setName(entity.getName());
        registrationProductDTO.setStartingPrice(entity.getStartingPrice());
        registrationProductDTO.setStatus(entity.getStatus());
        registrationProductDTO.setRegistrationDate(entity.getRegistrationDate());
        registrationProductDTO.setDescription(entity.getDescription());
        registrationProductDTO.setSeller(toSellerDTO(entity.getSeller()));
        registrationProductDTO.setSubCategory(toSubCategoryDTO(entity.getSubCategory()));
        registrationProductDTO.setAuctionFormat(toAuctionFormatDTO(entity.getAuctionFormat()));

        return registrationProductDTO;
    }

    public RegistrationProductEntity toEntity(RegistrationProductDTO dto) {
        RegistrationProductEntity registrationProductEntity = new RegistrationProductEntity();
        registrationProductEntity.setName(dto.getName());
        registrationProductEntity.setStartingPrice(dto.getStartingPrice());
        registrationProductEntity.setStatus("Pending");
        registrationProductEntity.setRegistrationDate(LocalDate.now());
        registrationProductEntity.setDescription(dto.getDescription());
        registrationProductEntity.setSeller(toSellerEntity(dto.getSeller()));
        registrationProductEntity.setSubCategory(toSubCategoryEntity(dto.getSubCategory()));
        registrationProductEntity.setAuctionFormat(toAuctionFormatEntity(dto.getAuctionFormat()));
        
        return registrationProductEntity;
    }

    private SellerEntity toSellerEntity(SellerDTO sellerDTO) {
        return sellerConverter.toEntity(sellerDTO);
    }

    private SellerDTO toSellerDTO(SellerEntity sellerEntity) {
        return sellerConverter.toDTO(sellerEntity);
    }

    private SubCategoryEntity toSubCategoryEntity(SubCategoryDTO subCategoryDTO) {
        return subCategoryConverter.toEntity(subCategoryDTO);
    }

    private SubCategoryDTO toSubCategoryDTO(SubCategoryEntity subCategoryEntity) {
        return subCategoryConverter.toDTO(subCategoryEntity);
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }
    
}

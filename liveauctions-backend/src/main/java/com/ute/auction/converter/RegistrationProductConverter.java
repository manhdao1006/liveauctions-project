package com.ute.auction.converter;

import java.time.LocalDate;

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

    private final SellerConverter sellerConverter;
    private final SubCategoryConverter subCategoryConverter;
    private final AuctionFormatConverter auctionFormatConverter;

    public RegistrationProductConverter(@Lazy SellerConverter sellerConverter,
            @Lazy SubCategoryConverter subCategoryConverter, @Lazy AuctionFormatConverter auctionFormatConverter) {
        this.sellerConverter = sellerConverter;
        this.subCategoryConverter = subCategoryConverter;
        this.auctionFormatConverter = auctionFormatConverter;
    }

    public RegistrationProductDTO toDTO(RegistrationProductEntity entity) {
        if (entity == null) {
            return null;
        }

        RegistrationProductDTO registrationProductDTO = new RegistrationProductDTO();
        registrationProductDTO.setRegistrationProductId(entity.getRegistrationProductId());
        registrationProductDTO.setRegistrationProductName(entity.getRegistrationProductName());
        registrationProductDTO.setStartingPrice(entity.getStartingPrice());
        registrationProductDTO.setStatus(entity.getStatus());
        registrationProductDTO.setRegistrationDate(entity.getRegistrationDate());
        registrationProductDTO.setDescription(entity.getDescription());
        registrationProductDTO.setDelFlag(entity.getDelFlag());
        registrationProductDTO.setSeller(toSellerDTO(entity.getSeller()));
        registrationProductDTO.setSubCategory(toSubCategoryDTO(entity.getSubCategory()));
        registrationProductDTO.setAuctionFormat(toAuctionFormatDTO(entity.getAuctionFormat()));

        return registrationProductDTO;
    }

    public RegistrationProductEntity toEntity(RegistrationProductDTO dto) {
        if (dto == null) {
            return null;
        }

        RegistrationProductEntity registrationProductEntity = new RegistrationProductEntity();
        registrationProductEntity.setRegistrationProductId(dto.getRegistrationProductId());
        registrationProductEntity.setRegistrationProductName(dto.getRegistrationProductName());
        registrationProductEntity.setStartingPrice(dto.getStartingPrice());
        registrationProductEntity.setStatus("Pending");
        registrationProductEntity.setRegistrationDate(LocalDate.now());
        registrationProductEntity.setDescription(dto.getDescription());
        registrationProductEntity.setDelFlag("1");
        registrationProductEntity.setSeller(toSellerEntity(dto.getSeller()));
        registrationProductEntity.setSubCategory(toSubCategoryEntity(dto.getSubCategory()));
        registrationProductEntity.setAuctionFormat(toAuctionFormatEntity(dto.getAuctionFormat()));

        return registrationProductEntity;
    }

    private SellerEntity toSellerEntity(SellerDTO sellerDTO) {
        if (sellerDTO == null) {
            return null;
        }
        return sellerConverter.toEntity(sellerDTO);
    }

    private SellerDTO toSellerDTO(SellerEntity sellerEntity) {
        if (sellerEntity == null) {
            return null;
        }
        return sellerConverter.toDTO(sellerEntity);
    }

    private SubCategoryEntity toSubCategoryEntity(SubCategoryDTO subCategoryDTO) {
        if (subCategoryDTO == null) {
            return null;
        }
        return subCategoryConverter.toEntity(subCategoryDTO);
    }

    private SubCategoryDTO toSubCategoryDTO(SubCategoryEntity subCategoryEntity) {
        if (subCategoryEntity == null) {
            return null;
        }
        return subCategoryConverter.toDTO(subCategoryEntity);
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        if (auctionFormatDTO == null) {
            return null;
        }
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        if (auctionFormatEntity == null) {
            return null;
        }
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }

}

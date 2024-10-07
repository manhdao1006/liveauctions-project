package com.ute.auction.converter;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.dto.ImageDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.dto.SellerDTO;
import com.ute.auction.dto.SubCategoryDTO;
import com.ute.auction.dto.WareHouseDTO;
import com.ute.auction.entity.AppraiserEntity;
import com.ute.auction.entity.AuctionFormatEntity;
import com.ute.auction.entity.ImageEntity;
import com.ute.auction.entity.ProductEntity;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.SubCategoryEntity;
import com.ute.auction.entity.WareHouseEntity;

@Component
public class ProductConverter {

    private final ImageConverter imageConverter;
    private final AppraiserConverter appraiserConverter;
    private final WareHouseConverter wareHouseConverter;
    private final SellerConverter sellerConverter;
    private final SubCategoryConverter subCategoryConverter;
    private final AuctionFormatConverter auctionFormatConverter;

    public ProductConverter(@Lazy ImageConverter imageConverter, @Lazy AppraiserConverter appraiserConverter,
            @Lazy WareHouseConverter wareHouseConverter, @Lazy SellerConverter sellerConverter,
            @Lazy SubCategoryConverter subCategoryConverter, @Lazy AuctionFormatConverter auctionFormatConverter) {
        this.imageConverter = imageConverter;
        this.appraiserConverter = appraiserConverter;
        this.wareHouseConverter = wareHouseConverter;
        this.sellerConverter = sellerConverter;
        this.subCategoryConverter = subCategoryConverter;
        this.auctionFormatConverter = auctionFormatConverter;
    }

    public ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(entity.getProductId());
        productDTO.setProductName(entity.getProductName());
        productDTO.setStartingPrice(entity.getStartingPrice());
        productDTO.setDescription(entity.getDescription());
        productDTO.setStatus(entity.getStatus());
        productDTO.setMinPrice(entity.getMinPrice());
        productDTO.setMaxPrice(entity.getMaxPrice());
        productDTO.setDelFlag(entity.getDelFlag());
        productDTO.setSeller(toSellerDTO(entity.getSeller()));
        productDTO.setSubCategory(toSubCategoryDTO(entity.getSubCategory()));
        productDTO.setAuctionFormat(toAuctionFormatDTO(entity.getAuctionFormat()));
        productDTO.setWareHouse(toWareHouseDTO(entity.getWareHouse()));
        productDTO.setAppraiser(toAppraiserDTO(entity.getAppraiser()));
        productDTO.setImages(toImagesDTO(entity.getImages()));

        return productDTO;
    }

    public ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(dto.getProductId());
        productEntity.setProductName(dto.getProductName());
        productEntity.setStartingPrice(dto.getStartingPrice());
        productEntity.setDescription(dto.getDescription());
        productEntity.setStatus(dto.getStatus());
        productEntity.setMinPrice(dto.getMinPrice());
        productEntity.setMaxPrice(dto.getMaxPrice());
        productEntity.setDelFlag("1");
        productEntity.setSeller(toSellerEntity(dto.getSeller()));
        productEntity.setSubCategory(toSubCategoryEntity(dto.getSubCategory()));
        productEntity.setAuctionFormat(toAuctionFormatEntity(dto.getAuctionFormat()));
        productEntity.setWareHouse(toWareHouseEntity(dto.getWareHouse()));
        productEntity.setAppraiser(toAppraiserEntity(dto.getAppraiser()));
        productEntity.setImages(toImagesEntity(dto.getImages()));

        return productEntity;
    }

    private List<ImageDTO> toImagesDTO(List<ImageEntity> imagesEntity) {
        if (imagesEntity == null) {
            return null;
        }
        return imageConverter.toDTOs(imagesEntity);
    }

    private List<ImageEntity> toImagesEntity(List<ImageDTO> imagesDTO) {
        if (imagesDTO == null) {
            return null;
        }
        return imageConverter.toEntities(imagesDTO);
    }

    private AppraiserDTO toAppraiserDTO(AppraiserEntity appraiserEntity) {
        if (appraiserEntity == null) {
            return null;
        }
        return appraiserConverter.toDTO(appraiserEntity);
    }

    private WareHouseDTO toWareHouseDTO(WareHouseEntity wareHouseEntity) {
        if (wareHouseEntity == null) {
            return null;
        }
        return wareHouseConverter.toDTO(wareHouseEntity);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        if (auctionFormatEntity == null) {
            return null;
        }
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }

    private SubCategoryDTO toSubCategoryDTO(SubCategoryEntity subCategoryEntity) {
        if (subCategoryEntity == null) {
            return null;
        }
        return subCategoryConverter.toDTO(subCategoryEntity);
    }

    private SellerDTO toSellerDTO(SellerEntity sellerEntity) {
        if (sellerEntity == null) {
            return null;
        }
        return sellerConverter.toDTO(sellerEntity);
    }

    private AppraiserEntity toAppraiserEntity(AppraiserDTO appraiserDTO) {
        if (appraiserDTO == null) {
            return null;
        }
        return appraiserConverter.toEntity(appraiserDTO);
    }

    private WareHouseEntity toWareHouseEntity(WareHouseDTO wareHouseDTO) {
        if (wareHouseDTO == null) {
            return null;
        }
        return wareHouseConverter.toEntity(wareHouseDTO);
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        if (auctionFormatDTO == null) {
            return null;
        }
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private SubCategoryEntity toSubCategoryEntity(SubCategoryDTO subCategoryDTO) {
        if (subCategoryDTO == null) {
            return null;
        }
        return subCategoryConverter.toEntity(subCategoryDTO);
    }

    private SellerEntity toSellerEntity(SellerDTO sellerDTO) {
        if (sellerDTO == null) {
            return null;
        }
        return sellerConverter.toEntity(sellerDTO);
    }

}

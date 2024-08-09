package com.ute.auction.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.dto.ImagesDTO;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.dto.SellerDTO;
import com.ute.auction.dto.SubCategoryDTO;
import com.ute.auction.dto.WareHouseDTO;
import com.ute.auction.entity.AppraiserEntity;
import com.ute.auction.entity.AuctionFormatEntity;
import com.ute.auction.entity.ImagesEntity;
import com.ute.auction.entity.ProductEntity;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.SubCategoryEntity;
import com.ute.auction.entity.WareHouseEntity;

@Component
public class ProductConverter {

    private ImagesConverter imagesConverter;
    private AppraiserConverter appraiserConverter;
    private WareHouseConverter wareHouseConverter;
    private SellerConverter sellerConverter;
    private SubCategoryConverter subCategoryConverter;
    private AuctionFormatConverter auctionFormatConverter;

    @Autowired
    public void setImagesConverter(@Lazy ImagesConverter imagesConverter) {
        this.imagesConverter = imagesConverter;
    }

    @Autowired
    public void setAppraiserConverter(@Lazy AppraiserConverter appraiserConverter) {
        this.appraiserConverter = appraiserConverter;
    }

    @Autowired
    public void setWareHouseConverter(@Lazy WareHouseConverter wareHouseConverter) {
        this.wareHouseConverter = wareHouseConverter;
    }

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

    public ProductDTO toDTO(ProductEntity entity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(entity.getId());
        productDTO.setName(entity.getName());
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
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(dto.getId());
        productEntity.setName(dto.getName());
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

    private List<ImagesDTO> toImagesDTO(List<ImagesEntity> imagesEntity) {
        return imagesConverter.toDTOs(imagesEntity);
    }

    private List<ImagesEntity> toImagesEntity(List<ImagesDTO> imagesDTO) {
        return imagesConverter.toEntities(imagesDTO);
    }

    private AppraiserDTO toAppraiserDTO(AppraiserEntity appraiserEntity) {
        return appraiserConverter.toDTO(appraiserEntity);
    }

    private WareHouseDTO toWareHouseDTO(WareHouseEntity wareHouseEntity) {
        return wareHouseConverter.toDTO(wareHouseEntity);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }

    private SubCategoryDTO toSubCategoryDTO(SubCategoryEntity subCategoryEntity) {
        return subCategoryConverter.toDTO(subCategoryEntity);
    }

    private SellerDTO toSellerDTO(SellerEntity sellerEntity) {
        return sellerConverter.toDTO(sellerEntity);
    }

    private AppraiserEntity toAppraiserEntity(AppraiserDTO appraiserDTO) {
        return appraiserConverter.toEntity(appraiserDTO);
    }

    private WareHouseEntity toWareHouseEntity(WareHouseDTO wareHouseDTO) {
        return wareHouseConverter.toEntity(wareHouseDTO);
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private SubCategoryEntity toSubCategoryEntity(SubCategoryDTO subCategoryDTO) {
        return subCategoryConverter.toEntity(subCategoryDTO);
    }

    private SellerEntity toSellerEntity(SellerDTO sellerDTO) {
        return sellerConverter.toEntity(sellerDTO);
    }

}

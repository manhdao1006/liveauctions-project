package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionDTO;
import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.dto.StaffDTO;
import com.ute.auction.entity.AuctionEntity;
import com.ute.auction.entity.AuctionFormatEntity;
import com.ute.auction.entity.StaffEntity;

@Component
public class AuctionConverter {

    private final StaffConverter staffConverter;
    private final AuctionFormatConverter auctionFormatConverter;

    public AuctionConverter(@Lazy StaffConverter staffConverter, @Lazy AuctionFormatConverter auctionFormatConverter) {
        this.staffConverter = staffConverter;
        this.auctionFormatConverter = auctionFormatConverter;
    }

    public AuctionDTO toDTO(AuctionEntity entity) {
        if (entity == null) {
            return null;
        }

        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setAuctionId(entity.getAuctionId());
        auctionDTO.setAuctionName(entity.getAuctionName());
        auctionDTO.setStartDate(entity.getStartDate());
        auctionDTO.setEndDate(entity.getEndDate());
        auctionDTO.setBuyerPremium(entity.getBuyerPremium());
        auctionDTO.setStatus(entity.getStatus());
        auctionDTO.setDescription(entity.getDescription());
        auctionDTO.setDelFlag(entity.getDelFlag());
        auctionDTO.setStaff(toStaffDTO(entity.getStaff()));
        auctionDTO.setAuctionFormat(toAuctionFormatDTO(entity.getAuctionFormat()));

        return auctionDTO;
    }

    public AuctionEntity toEntity(AuctionDTO dto) {
        if (dto == null) {
            return null;
        }

        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setAuctionId(dto.getAuctionId());
        auctionEntity.setAuctionName(dto.getAuctionName());
        auctionEntity.setStartDate(dto.getStartDate());
        auctionEntity.setEndDate(dto.getEndDate());
        auctionEntity.setBuyerPremium(dto.getBuyerPremium());
        auctionEntity.setStatus(dto.getStatus());
        auctionEntity.setDescription(dto.getDescription());
        auctionEntity.setDelFlag("1");
        auctionEntity.setStaff(toStaffEntity(dto.getStaff()));
        auctionEntity.setAuctionFormat(toAuctionFormatEntity(dto.getAuctionFormat()));

        return auctionEntity;
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        if (auctionFormatDTO == null) {
            return null;
        }
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private StaffEntity toStaffEntity(StaffDTO staffDTO) {
        if (staffDTO == null) {
            return null;
        }
        return staffConverter.toEntity(staffDTO);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        if (auctionFormatEntity == null) {
            return null;
        }
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }

    private StaffDTO toStaffDTO(StaffEntity staffEntity) {
        if (staffEntity == null) {
            return null;
        }
        return staffConverter.toDTO(staffEntity);
    }

}

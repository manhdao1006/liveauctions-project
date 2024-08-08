package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
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

    private StaffConverter staffConverter;
    private AuctionFormatConverter auctionFormatConverter;

    @Autowired
    public void setStaffConverter(@Lazy StaffConverter staffConverter) {
        this.staffConverter = staffConverter;
    }

    @Autowired
    public void setAuctionFormatConverter(@Lazy AuctionFormatConverter auctionFormatConverter) {
        this.auctionFormatConverter = auctionFormatConverter;
    }    

    public AuctionDTO toDTO(AuctionEntity entity) {
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setId(entity.getId());
        auctionDTO.setName(entity.getName());
        auctionDTO.setStartDate(entity.getStartDate());
        auctionDTO.setEndDate(entity.getEndDate());
        auctionDTO.setBuyerPremium(entity.getBuyerPremium());
        auctionDTO.setStatus(entity.getStatus());
        auctionDTO.setDescription(entity.getDescription());
        auctionDTO.setStaff(toStaffDTO(entity.getStaff()));
        auctionDTO.setAuctionFormat(toAuctionFormatDTO(entity.getAuctionFormat()));

        return auctionDTO;
    }

    public AuctionEntity toEntity(AuctionDTO dto) {
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setId(dto.getId());
        auctionEntity.setName(dto.getName());
        auctionEntity.setStartDate(dto.getStartDate());
        auctionEntity.setEndDate(dto.getEndDate());
        auctionEntity.setBuyerPremium(dto.getBuyerPremium());
        auctionEntity.setStatus(dto.getStatus());
        auctionEntity.setDescription(dto.getDescription());
        auctionEntity.setStaff(toStaffEntity(dto.getStaff()));
        auctionEntity.setAuctionFormat(toAuctionFormatEntity(dto.getAuctionFormat()));

        return auctionEntity;
    }

    private AuctionFormatEntity toAuctionFormatEntity(AuctionFormatDTO auctionFormatDTO) {
        return auctionFormatConverter.toEntity(auctionFormatDTO);
    }

    private StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return staffConverter.toEntity(staffDTO);
    }

    private AuctionFormatDTO toAuctionFormatDTO(AuctionFormatEntity auctionFormatEntity) {
        return auctionFormatConverter.toDTO(auctionFormatEntity);
    }

    private StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return staffConverter.toDTO(staffEntity);
    }
    
}

package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.entity.AuctionFormatEntity;

@Component
public class AuctionFormatConverter {

    public AuctionFormatDTO toDTO(AuctionFormatEntity entity) {
        if (entity == null) {
            return null;
        }

        AuctionFormatDTO auctionFormatDTO = new AuctionFormatDTO();
        auctionFormatDTO.setAuctionFormatId(entity.getAuctionFormatId());
        auctionFormatDTO.setAuctionFormatName(entity.getAuctionFormatName());
        auctionFormatDTO.setDelFlag(entity.getDelFlag());

        return auctionFormatDTO;
    }

    public AuctionFormatEntity toEntity(AuctionFormatDTO dto) {
        if (dto == null) {
            return null;
        }

        AuctionFormatEntity auctionFormatEntity = new AuctionFormatEntity();
        auctionFormatEntity.setAuctionFormatId(dto.getAuctionFormatId());
        auctionFormatEntity.setAuctionFormatName(dto.getAuctionFormatName());
        auctionFormatEntity.setDelFlag("1");

        return auctionFormatEntity;
    }

}

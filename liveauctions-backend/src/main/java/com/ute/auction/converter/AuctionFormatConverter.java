package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.AuctionFormatDTO;
import com.ute.auction.entity.AuctionFormatEntity;

@Component
public class AuctionFormatConverter {

    public AuctionFormatDTO toDTO(AuctionFormatEntity entity) {
        AuctionFormatDTO auctionFormatDTO = new AuctionFormatDTO();
        auctionFormatDTO.setId(entity.getId());
        auctionFormatDTO.setName(entity.getName());

        return auctionFormatDTO;
    }

    public AuctionFormatEntity toEntity(AuctionFormatDTO dto) {
        AuctionFormatEntity auctionFormatEntity = new AuctionFormatEntity();
        auctionFormatEntity.setId(dto.getId());
        auctionFormatEntity.setName(dto.getName());

        return auctionFormatEntity;
    }
    
}

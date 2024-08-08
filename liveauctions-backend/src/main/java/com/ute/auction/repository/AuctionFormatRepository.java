package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.AuctionFormatEntity;

@Repository
public interface AuctionFormatRepository extends JpaRepository<AuctionFormatEntity, Long> {

}

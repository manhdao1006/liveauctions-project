package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SellerEntity;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    SellerEntity findOneBySellerId(int sellerId);

    boolean existsBySellerId(int sellerId);

}

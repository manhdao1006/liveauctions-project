package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.BuyerEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer> {

}

package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ute.auction.entity.BuyerEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer> {

    @Query("SELECT b FROM BuyerEntity b JOIN b.user u WHERE u.userId = ?1")
    Optional<BuyerEntity> findByBuyerId(int id);

    @Query("SELECT b FROM BuyerEntity b JOIN b.user u WHERE u.email = ?1")
    Optional<BuyerEntity> findByEmail(String email);

}

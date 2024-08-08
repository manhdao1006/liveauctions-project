package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SellerTaxEntity;
import com.ute.auction.entity.impl.SellerTaxId;

@Repository
public interface SellerTaxRepository extends JpaRepository<SellerTaxEntity, SellerTaxId> {

}

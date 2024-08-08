package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.TaxEntity;

@Repository
public interface TaxRepository extends JpaRepository<TaxEntity, Long> {

}

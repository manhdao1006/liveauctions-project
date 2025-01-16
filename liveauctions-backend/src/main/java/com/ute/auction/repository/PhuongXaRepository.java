package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.PhuongXaEntity;

@Repository
public interface PhuongXaRepository extends JpaRepository<PhuongXaEntity, Long> {

}

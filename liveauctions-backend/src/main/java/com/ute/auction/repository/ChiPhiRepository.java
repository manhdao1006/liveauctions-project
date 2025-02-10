package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.ChiPhiEntity;

public interface ChiPhiRepository extends JpaRepository<ChiPhiEntity, Long> {

    Optional<ChiPhiEntity> findOneByMaChiPhi(Long maChiPhi);

}

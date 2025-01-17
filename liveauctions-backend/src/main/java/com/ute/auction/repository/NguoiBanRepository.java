package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NguoiBanEntity;

@Repository
public interface NguoiBanRepository extends JpaRepository<NguoiBanEntity, Long> {

    Optional<NguoiBanEntity> findOneByMaNguoiBan(long sellerId);

    boolean existsByMaNguoiBan(long sellerId);

}

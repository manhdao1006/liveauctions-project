package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.VaiTroEntity;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTroEntity, Long> {

    Optional<VaiTroEntity> findByTenVaiTro(String name);

}

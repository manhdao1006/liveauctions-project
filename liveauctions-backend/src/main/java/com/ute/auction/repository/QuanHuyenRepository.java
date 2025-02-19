package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.QuanHuyenEntity;

@Repository
public interface QuanHuyenRepository extends JpaRepository<QuanHuyenEntity, Long> {

    @Query("SELECT q FROM QuanHuyenEntity q JOIN q.phuongXas p WHERE p.maPhuongXa = :maPhuongXa")
    Optional<QuanHuyenEntity> findByMaPhuongXa(@Param("maPhuongXa") Long maPhuongXa);

}

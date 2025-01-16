package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.LoaiDauGiaEntity;

@Repository
public interface LoaiDauGiaRepository extends JpaRepository<LoaiDauGiaEntity, Long> {

}

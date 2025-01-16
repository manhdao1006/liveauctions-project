package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.DanhMucEntity;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, Long> {

}

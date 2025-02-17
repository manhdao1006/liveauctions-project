package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.QuanHuyenEntity;

@Repository
public interface QuanHuyenRepository extends JpaRepository<QuanHuyenEntity, Long> {

}

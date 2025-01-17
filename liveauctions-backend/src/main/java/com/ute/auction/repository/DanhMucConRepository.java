package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.DanhMucConEntity;

@Repository
public interface DanhMucConRepository extends JpaRepository<DanhMucConEntity, Long> {

    Optional<DanhMucConEntity> findOneByMaDanhMucCon(long maDanhMucCon);

    void deleteByMaDanhMucCon(long maDanhMucCon);

}

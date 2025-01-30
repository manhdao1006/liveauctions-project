package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.NhanVienEntity;

public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Long> {

    Optional<NhanVienEntity> findOneByMaNhanVien(long maNhanVien);

}

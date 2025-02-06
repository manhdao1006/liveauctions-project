package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.NhanVienEntity;

public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Long> {

    Page<NhanVienEntity> findNhanViensByTrangThaiXoa(String trangThaiXoa, Pageable pageable);

    Optional<NhanVienEntity> findOneByMaNhanVien(long maNhanVien);

}

package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.NhanVienEntity;

public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Long> {

    List<NhanVienEntity> findNhanViensByTrangThaiXoa(String trangThaiXoa);

    Optional<NhanVienEntity> findOneByMaNhanVien(long maNhanVien);

}

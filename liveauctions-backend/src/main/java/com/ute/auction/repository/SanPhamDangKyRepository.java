package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamDangKyEntity;

@Repository
public interface SanPhamDangKyRepository extends JpaRepository<SanPhamDangKyEntity, Long> {

    Page<SanPhamDangKyEntity> findSanPhamDangKysByTrangThaiXoa(String trangThaiXoa, Pageable pageable);

    Optional<SanPhamDangKyEntity> findOneByMaSanPhamDangKyAndTrangThaiXoa(long maSanPhamDangKy,
            String trangThaiXoa);

    Page<SanPhamDangKyEntity> findSanPhamDangKysByNguoiBan_MaNguoiBanAndTrangThaiXoa(long maNguoiBan,
            String trangThaiXoa, Pageable pageable);

}

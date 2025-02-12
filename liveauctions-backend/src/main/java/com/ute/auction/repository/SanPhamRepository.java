package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamEntity;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPhamEntity, String> {

    List<SanPhamEntity> findSanPhamsByTrangThaiXoa(String trangThaiXoa);

    Optional<SanPhamEntity> findOneByMaSanPham(String maSanPham);

    @Query("SELECT s FROM SanPhamEntity s WHERE s.nguoiBan.maNguoiBan = :maNguoiBan AND s.trangThaiXoa = :trangThaiXoa")
    Page<SanPhamEntity> findSanPhamsByMaNguoiBan(long maNguoiBan, String trangThaiXoa, Pageable pageable);

}

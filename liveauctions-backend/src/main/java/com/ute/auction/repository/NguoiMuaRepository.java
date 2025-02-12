package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.NguoiMuaEntity;

public interface NguoiMuaRepository extends JpaRepository<NguoiMuaEntity, Long> {

    Page<NguoiMuaEntity> findNguoiMuasByTrangThaiXoa(String trangThaiXoa, Pageable pageable);

    Optional<NguoiMuaEntity> findOneByMaNguoiMua(long maNguoiMua);

}

package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.NguoiMuaEntity;

public interface NguoiMuaRepository extends JpaRepository<NguoiMuaEntity, Long> {

    List<NguoiMuaEntity> findNguoiMuasByTrangThaiXoa(String trangThaiXoa);

    Optional<NguoiMuaEntity> findOneByMaNguoiMua(long maNguoiMua);

}

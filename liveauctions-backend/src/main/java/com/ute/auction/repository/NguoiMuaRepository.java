package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ute.auction.entity.NguoiMuaEntity;

public interface NguoiMuaRepository extends JpaRepository<NguoiMuaEntity, Long> {

    Page<NguoiMuaEntity> findNguoiMuasByTrangThaiXoa(String trangThaiXoa, Pageable pageable);

    Optional<NguoiMuaEntity> findOneByMaNguoiMua(long maNguoiMua);

    @Query("SELECT b FROM NguoiMuaEntity b JOIN b.nguoiDung u WHERE u.email = ?1")
    Optional<NguoiMuaEntity> findByEmail(String email);

}

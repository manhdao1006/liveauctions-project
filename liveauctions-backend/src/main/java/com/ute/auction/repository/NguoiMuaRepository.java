package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ute.auction.entity.NguoiMuaEntity;

public interface NguoiMuaRepository extends JpaRepository<NguoiMuaEntity, Long> {

    @Query("SELECT b FROM NguoiMuaEntity b JOIN b.nguoiDung u WHERE u.maNguoiDung = ?1")
    Optional<NguoiMuaEntity> findByMaNguoiDung(long id);

    @Query("SELECT b FROM NguoiMuaEntity b JOIN b.nguoiDung u WHERE u.email = ?1")
    Optional<NguoiMuaEntity> findByEmail(String email);

}

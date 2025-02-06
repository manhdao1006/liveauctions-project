package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NguoiBanEntity;

@Repository
public interface NguoiBanRepository extends JpaRepository<NguoiBanEntity, Long> {

    Page<NguoiBanEntity> findNguoiBansByTrangThaiXoa(String trangThaiXoa, Pageable pageable);

    Optional<NguoiBanEntity> findOneByMaNguoiBan(long maNguoiBan);

    boolean existsByMaNguoiBan(long maNguoiBan);

}

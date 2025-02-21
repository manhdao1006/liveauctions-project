package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NguoiBanEntity;

@Repository
public interface NguoiBanRepository extends JpaRepository<NguoiBanEntity, Long> {

    List<NguoiBanEntity> findNguoiBansByTrangThaiXoa(String trangThaiXoa);

    Optional<NguoiBanEntity> findOneByMaNguoiBan(long maNguoiBan);

    boolean existsByMaNguoiBan(long maNguoiBan);

}

package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NhaKhoEntity;

@Repository
public interface NhaKhoRepository extends JpaRepository<NhaKhoEntity, Long> {

    List<NhaKhoEntity> findNhaKhosByTrangThaiXoa(String trangThaiXoa);

    Optional<NhaKhoEntity> findOneByMaNhaKho(long maNhaKho);

}

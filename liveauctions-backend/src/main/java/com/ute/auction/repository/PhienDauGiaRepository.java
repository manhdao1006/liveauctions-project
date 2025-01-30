package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.PhienDauGiaEntity;

@Repository
public interface PhienDauGiaRepository extends JpaRepository<PhienDauGiaEntity, Long> {

    List<PhienDauGiaEntity> findPhienDauGiasByTrangThaiXoa(String trangThaiXoa);

    Optional<PhienDauGiaEntity> findOneByMaPhienDauGia(long maPhienDauGia);

}

package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.DanhMucEntity;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, Long> {

    List<DanhMucEntity> findDanhMucsByTrangThaiXoa(String trangThaiXoa);

    Optional<DanhMucEntity> findOneByMaDanhMuc(long maDanhMuc);

    void deleteByMaDanhMuc(long maDanhMuc);

    @Query("SELECT dm FROM DanhMucEntity dm JOIN dm.danhMucCons dmc WHERE dmc.maDanhMucCon = :maDanhMucCon")
    Optional<DanhMucEntity> findByMaDanhMucCon(@Param("maDanhMucCon") Long maDanhMucCon);

}

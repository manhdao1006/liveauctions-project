package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.DanhMucConEntity;

@Repository
public interface DanhMucConRepository extends JpaRepository<DanhMucConEntity, Long> {

    List<DanhMucConEntity> findDanhMucConsByTrangThaiXoa(String trangThaiXoa);

    List<DanhMucConEntity> findDanhMucConsByTrangThaiXoaAndDanhMuc_MaDanhMuc(String trangThaiXoa, long maDanhMuc);

    Optional<DanhMucConEntity> findOneByMaDanhMucCon(long maDanhMucCon);

    void deleteByMaDanhMucCon(long maDanhMucCon);

}

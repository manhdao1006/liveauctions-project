package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.AnhSanPhamEntity;
import com.ute.auction.entity.SanPhamEntity;

@Repository
public interface AnhSanPhamRepository extends JpaRepository<AnhSanPhamEntity, Long> {

    List<AnhSanPhamEntity> findOneBySanPham(SanPhamEntity sanPhamEntity);

    Optional<AnhSanPhamEntity> findOneByTenAnh(String tenAnh);

}

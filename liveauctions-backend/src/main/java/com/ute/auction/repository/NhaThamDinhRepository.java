package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NhaThamDinhEntity;

@Repository
public interface NhaThamDinhRepository extends JpaRepository<NhaThamDinhEntity, Long> {

    List<NhaThamDinhEntity> findNhaThamDinhsByTrangThaiXoa(String trangThaiXoa);

    Optional<NhaThamDinhEntity> findOneByMaNhaThamDinh(long maNhaThamDinh);

    Optional<NhaThamDinhEntity> findOneByEmail(String email);

    @Query(value = "SELECT ap.* FROM nha_tham_dinh ap WHERE " +
            "ap.hoVaTen LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.email LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.gioiTinh LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.soDienThoai LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.diaChi LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.ngaySinh LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.moTa LIKE CONCAT('%', :keyword, '%')", countQuery = "SELECT COUNT(ap.id) FROM nha_tham_dinh ap WHERE "
                    +
                    "ap.hoVaTen LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.email LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.gioiTinh LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.soDienThoai LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.diaChi LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.ngaySinh LIKE CONCAT('%', :keyword, '%') OR " +
                    "ap.moTa LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    Page<NhaThamDinhEntity> searchNhaThamDinh(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT ap.* FROM nha_tham_dinh ap WHERE " +
            "ap.hoVaTen LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.email LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.gioiTinh LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.soDienThoai LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.diaChi LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.ngaySinh LIKE CONCAT('%', :keyword, '%') OR " +
            "ap.moTa LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<NhaThamDinhEntity> existsNhaThamDinh(@Param("keyword") String keyword);

}

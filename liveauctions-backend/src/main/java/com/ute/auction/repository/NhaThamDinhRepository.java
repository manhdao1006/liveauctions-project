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
        Page<NhaThamDinhEntity> searchAppraiser(@Param("keyword") String keyword, Pageable pageable);

        @Query(value = "SELECT ap.* FROM nha_tham_dinh ap WHERE " +
                        "ap.hoVaTen LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.email LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.gioiTinh LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.soDienThoai LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.diaChi LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.ngaySinh LIKE CONCAT('%', :keyword, '%') OR " +
                        "ap.moTa LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
        List<NhaThamDinhEntity> existsAppraiser(@Param("keyword") String keyword);

        @Query(value = "SELECT ap.* FROM nha_tham_dinh ap ORDER BY ap.hoVaTen ASC", countQuery = "SELECT COUNT(ap.maNhaThamDinh) FROM nha_tham_dinh ap", nativeQuery = true)
        Page<NhaThamDinhEntity> sortedAscByName(Pageable pageable);

        @Query(value = "SELECT ap.* FROM nha_tham_dinh ap ORDER BY ap.hoVaTen DESC", countQuery = "SELECT COUNT(ap.maNhaThamDinh) FROM nha_tham_dinh ap", nativeQuery = true)
        Page<NhaThamDinhEntity> sortedDescByName(Pageable pageable);

        @Query(value = "SELECT ap.* FROM nha_tham_dinh ap ORDER BY ap.ngaySinh ASC", countQuery = "SELECT COUNT(ap.maNhaThamDinh) FROM nha_tham_dinh ap", nativeQuery = true)
        Page<NhaThamDinhEntity> sortedAscByDoB(Pageable pageable);

        @Query(value = "SELECT ap.* FROM nha_tham_dinh ap ORDER BY ap.ngaySinh DESC", countQuery = "SELECT COUNT(ap.maNhaThamDinh) FROM nha_tham_dinh ap", nativeQuery = true)
        Page<NhaThamDinhEntity> sortedDescByDoB(Pageable pageable);

}

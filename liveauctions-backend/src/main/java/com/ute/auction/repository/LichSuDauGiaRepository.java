package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.LichSuDauGiaEntity;
import com.ute.auction.entity.impl.MaLichSuDauGia;

@Repository
public interface LichSuDauGiaRepository extends JpaRepository<LichSuDauGiaEntity, MaLichSuDauGia> {

    Optional<LichSuDauGiaEntity> findOneByMaLichSuDauGia_MaPhienDauGiaAndMaLichSuDauGia_MaSanPhamAndMaLichSuDauGia_MaNguoiMuaAndMaLichSuDauGia_MaChiPhi(
            long maPhienDauGia, String maSanPham, long maNguoiMua, long maChiPhi);

    @Query(value = "SELECT * FROM lich_su_dau_gia WHERE maNguoiMua = :maNguoiMua", nativeQuery = true)
    Page<LichSuDauGiaEntity> findByMaNguoiMua(@Param("maNguoiMua") long maNguoiMua, Pageable pageable);

    @Query(value = """
            SELECT lsdg.*
            FROM lich_su_dau_gia lsdg
            JOIN san_pham sp ON lsdg.maSanPham = sp.maSanPham
            WHERE sp.maNguoiBan = :maNguoiBan
            """, nativeQuery = true)
    Page<LichSuDauGiaEntity> findByMaNguoiBan(@Param("maNguoiBan") long maNguoiBan, Pageable pageable);

    @Query(value = "SELECT * FROM lich_su_dau_gia WHERE maNguoiMua = :maNguoiMua AND trangThaiDonHang = :trangThaiDonHang", nativeQuery = true)
    Page<LichSuDauGiaEntity> findByTrangThaiDonHangNguoiMua(@Param("maNguoiMua") long maNguoiMua,
            @Param("trangThaiDonHang") String trangThaiDonHang, Pageable pageable);

    @Query(value = """
            SELECT lsdg.*
            FROM lich_su_dau_gia lsdg
            JOIN san_pham sp ON lsdg.maSanPham = sp.maSanPham
            WHERE sp.maNguoiBan = :maNguoiBan
            AND lsdg.trangThaiDonHang = :trangThaiDonHang
            """, nativeQuery = true)
    Page<LichSuDauGiaEntity> findByTrangThaiDonHangNguoiBan(@Param("maNguoiBan") long maNguoiBan,
            @Param("trangThaiDonHang") String trangThaiDonHang, Pageable pageable);

}

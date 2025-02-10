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
        Page<LichSuDauGiaEntity> findByMaNguoiMua(@Param("maNguoiMua") Long maNguoiMua, Pageable pageable);

        @Query(value = """
                        SELECT lsdg.*
                        FROM lich_su_dau_gia lsdg
                        JOIN san_pham sp ON lsdg.maSanPham = sp.maSanPham
                        WHERE sp.maNguoiBan = :maNguoiBan
                        """, nativeQuery = true)
        Page<LichSuDauGiaEntity> findByMaNguoiBan(@Param("maNguoiBan") Long maNguoiBan, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDonHang like ?2 AND ah.trangThaiDauGia like 'done'", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDonHang like ?2 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> findOrdersByOrderStatus(long sellerId, String orderStatus, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.giaDaDauGia ASC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedAscByAuctionedPrice(long sellerId, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.giaDaDauGia DESC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedDescByAuctionedPrice(long sellerId, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.ngayDatHang ASC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedAscByOrderDate(long sellerId, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.ngayDatHang DESC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedDescByOrderDate(long sellerId, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.ngayGiaoHang ASC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedAscByDeliveryDate(long sellerId, Pageable pageable);

        @Query(value = "SELECT ah.maPhienDauGia, ah.maNguoiMua, ah.maChiPhi, ah.maSanPham, ah.thoiGianDauGia, ah.ngayGiaoHang, ah.trangThaiDauGia,"
                        +
                        " au.maPhienDauGia, au.tenPhienDauGia, p.tenSanPham, u.hoVaTen, ah.giaDaDauGia, ah.ngayDatHang, ah.trangThaiDonHang"
                        +
                        " FROM lich_su_dau_gia ah" +
                        " JOIN phien_dau_gia au ON ah.maPhienDauGia = au.maPhienDauGia" +
                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                        " JOIN nguoi_mua b ON ah.maNguoiMua = b.maNguoiMua" +
                        " JOIN chi_phi f ON ah.maChiPhi = f.maChiPhi" +
                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                        " JOIN nguoi_dung u ON b.maNguoiMua = u.maNguoiMua" +
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'" +
                        " ORDER BY ah.ngayGiaoHang DESC", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> sortedDescByDeliveryDate(long sellerId, Pageable pageable);

}

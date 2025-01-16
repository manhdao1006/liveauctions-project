package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.LichSuDauGiaEntity;
import com.ute.auction.entity.impl.MaLichSuDauGia;

@Repository
public interface LichSuDauGiaRepository extends JpaRepository<LichSuDauGiaEntity, MaLichSuDauGia> {

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
                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", countQuery = "SELECT COUNT(ah.maSanPham) FROM lich_su_dau_gia ah"
                                        +
                                        " JOIN san_pham p ON ah.maSanPham = p.id" +
                                        " JOIN nguoi_ban s ON p.maNguoiBan = s.maNguoiBan" +
                                        " WHERE s.maNguoiBan like ?1 AND ah.trangThaiDauGia like 'done'", nativeQuery = true)
        Page<LichSuDauGiaEntity> findOrdersBySellerId(long sellerId, Pageable pageable);

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

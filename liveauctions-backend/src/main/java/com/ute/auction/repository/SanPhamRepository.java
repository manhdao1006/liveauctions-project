package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamEntity;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPhamEntity, String> {

        @Query(value = "SELECT p.maSanPham, p.tenSanPham, p.giaKhoiDiem, p.trangThai, p.giaNhoNhat, p.giaLonNhat, p.moTa, p.trangThaiXoa, p.maNguoiBan, p.maDanhMucCon, p.maLoaiDauGia, p.maNhaKho, p.maNhaThamDinh, "
                        +
                        "ap.maPhienDauGia " +
                        "FROM san_pham p " +
                        "LEFT JOIN nha_kho w ON p.maNhaKho = w.maNhaKho " +
                        "LEFT JOIN sanpham_daugia ap ON p.maSanPham = ap.maSanPham " +
                        "LEFT JOIN phien_dau_gia au ON ap.maPhienDauGia = au.maPhienDauGia " +
                        "LEFT JOIN anh_san_pham i ON p.maSanPham = i.maSanPham " +
                        "LEFT JOIN nha_tham_dinh a ON p.maNhaThamDinh = a.maNhaThamDinh " +
                        "WHERE p.maNguoiBan like ?1", countQuery = "SELECT COUNT(p.maSanPham) FROM san_pham p WHERE p.maNguoiBan like ?1", nativeQuery = true)
        Page<SanPhamEntity> findProductsBySellerId(long sellerId, Pageable pageable);

        @Query(value = "SELECT p.maSanPham, p.tenSanPham, p.giaKhoiDiem, p.trangThai, p.giaNhoNhat, p.giaLonNhat, p.moTa, p.trangThaiXoa, p.maNguoiBan, p.maDanhMucCon, p.maLoaiDauGia, p.maNhaKho, p.maNhaThamDinh, "
                        +
                        "ap.maPhienDauGia " +
                        "FROM san_pham p " +
                        "LEFT JOIN nha_kho w ON p.maNhaKho = w.maNhaKho " +
                        "LEFT JOIN sanpham_daugia ap ON p.maSanPham = ap.maSanPham " +
                        "LEFT JOIN phien_dau_gia au ON ap.maPhienDauGia = au.maPhienDauGia " +
                        "LEFT JOIN anh_san_pham i ON p.maSanPham = i.maSanPham " +
                        "LEFT JOIN nha_tham_dinh a ON p.maNhaThamDinh = a.maNhaThamDinh " +
                        "WHERE p.maNguoiBan like ?1 " +
                        "ORDER BY p.giaKhoiDiem ASC", countQuery = "SELECT COUNT(p.maSanPham) FROM san_pham p WHERE p.maNguoiBan like ?1", nativeQuery = true)
        Page<SanPhamEntity> sortedAscByStartingPrice(long sellerId, Pageable pageable);

        @Query(value = "SELECT p.maSanPham, p.tenSanPham, p.giaKhoiDiem, p.trangThai, p.giaNhoNhat, p.giaLonNhat, p.moTa, p.trangThaiXoa, p.maNguoiBan, p.maDanhMucCon, p.maLoaiDauGia, p.maNhaKho, p.maNhaThamDinh, "
                        +
                        "ap.maPhienDauGia " +
                        "FROM san_pham p " +
                        "LEFT JOIN nha_kho w ON p.maNhaKho = w.maNhaKho " +
                        "LEFT JOIN sanpham_daugia ap ON p.maSanPham = ap.maSanPham " +
                        "LEFT JOIN phien_dau_gia au ON ap.maPhienDauGia = au.maPhienDauGia " +
                        "LEFT JOIN anh_san_pham i ON p.maSanPham = i.maSanPham " +
                        "LEFT JOIN nha_tham_dinh a ON p.maNhaThamDinh = a.maNhaThamDinh " +
                        "WHERE p.maNguoiBan like ?1 " +
                        "ORDER BY p.giaKhoiDiem DESC", countQuery = "SELECT COUNT(p.maSanPham) FROM san_pham p WHERE p.maNguoiBan like ?1", nativeQuery = true)
        Page<SanPhamEntity> sortedDescByStartingPrice(long sellerId, Pageable pageable);

}

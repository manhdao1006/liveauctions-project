package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamDangKyEntity;

@Repository
public interface SanPhamDangKyRepository extends JpaRepository<SanPhamDangKyEntity, Long> {

        @Query(value = "SELECT rp.maSanPhamDangKy, rp.tenSanPham, rp.giaKhoiDiem, rp.trangThaiDangKy, rp.ngayDangKy, rp.moTa, rp.trangThaiXoa, rp.maNguoiBan, rp.maDanhMucCon, rp.maLoaiDauGia "
                        +
                        "FROM sanpham_dangky rp " +
                        "WHERE rp.maNguoiBan like ?1", countQuery = "SELECT COUNT(rp.maSanPhamDangKy) FROM sanpham_dangky rp WHERE rp.maNguoiBan = ?1", nativeQuery = true)
        Page<SanPhamDangKyEntity> findRegistrationProductsBySellerId(long sellerId, Pageable pageable);

        @Query(value = "SELECT rp.maSanPhamDangKy, rp.tenSanPham, rp.giaKhoiDiem, rp.trangThaiDangKy, rp.ngayDangKy, rp.moTa, rp.trangThaiXoa, rp.maNguoiBan, rp.maDanhMucCon, rp.maLoaiDauGia "
                        +
                        "FROM sanpham_dangky rp " +
                        "WHERE rp.maNguoiBan like ?1 " +
                        "ORDER BY rp.giaKhoiDiem ASC", countQuery = "SELECT COUNT(rp.maSanPhamDangKy) FROM sanpham_dangky rp WHERE rp.maNguoiBan = ?1", nativeQuery = true)
        Page<SanPhamDangKyEntity> sortedAscByStartingPrice(long sellerId, Pageable pageable);

        @Query(value = "SELECT rp.maSanPhamDangKy, rp.tenSanPham, rp.giaKhoiDiem, rp.trangThaiDangKy, rp.ngayDangKy, rp.moTa, rp.trangThaiXoa, rp.maNguoiBan, rp.maDanhMucCon, rp.maLoaiDauGia "
                        +
                        "FROM sanpham_dangky rp " +
                        "WHERE rp.maNguoiBan like ?1 " +
                        "ORDER BY rp.giaKhoiDiem DESC", countQuery = "SELECT COUNT(rp.maSanPhamDangKy) FROM sanpham_dangky rp WHERE rp.maNguoiBan = ?1", nativeQuery = true)
        Page<SanPhamDangKyEntity> sortedDescByStartingPrice(long sellerId, Pageable pageable);

        @Query(value = "SELECT rp.maSanPhamDangKy, rp.tenSanPham, rp.giaKhoiDiem, rp.trangThaiDangKy, rp.ngayDangKy, rp.moTa, rp.trangThaiXoa, rp.maNguoiBan, rp.maDanhMucCon, rp.maLoaiDauGia "
                        +
                        "FROM sanpham_dangky rp " +
                        "WHERE rp.maNguoiBan like ?1 " +
                        "ORDER BY rp.ngayDangKy ASC", countQuery = "SELECT COUNT(rp.maSanPhamDangKy) FROM sanpham_dangky rp WHERE rp.maNguoiBan = ?1", nativeQuery = true)
        Page<SanPhamDangKyEntity> sortedAscByRegistrationDate(long sellerId, Pageable pageable);

        @Query(value = "SELECT rp.maSanPhamDangKy, rp.tenSanPham, rp.giaKhoiDiem, rp.trangThaiDangKy, rp.ngayDangKy, rp.moTa, rp.trangThaiXoa, rp.maNguoiBan, rp.maDanhMucCon, rp.maLoaiDauGia "
                        +
                        "FROM sanpham_dangky rp " +
                        "WHERE rp.maNguoiBan like ?1 " +
                        "ORDER BY rp.ngayDangKy DESC", countQuery = "SELECT COUNT(rp.maSanPhamDangKy) FROM sanpham_dangky rp WHERE rp.maNguoiBan = ?1", nativeQuery = true)
        Page<SanPhamDangKyEntity> sortedDescByRegistrationDate(long sellerId, Pageable pageable);

}

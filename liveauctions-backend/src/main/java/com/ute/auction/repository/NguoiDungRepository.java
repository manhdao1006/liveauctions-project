package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.NguoiDungEntity;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDungEntity, Long> {

        @Query("SELECT u FROM NguoiDungEntity u " +
                        "JOIN u.phuongXa c " +
                        "JOIN c.quanHuyen st " +
                        "LEFT JOIN u.nhanVien staff " +
                        "LEFT JOIN u.nguoiBan seller " +
                        "LEFT JOIN u.nguoiMua buyer " +
                        "WHERE u.maNguoiDung = ?1")
        NguoiDungEntity findByUserId(long userId);

        @Query("SELECT u FROM NguoiDungEntity u JOIN u.vaiTros r WHERE r.maVaiTro = ?1")
        List<NguoiDungEntity> findNguoiDungsByVaiTro(long roleId);

        Optional<NguoiDungEntity> findByEmail(String email);

        Boolean existsByEmail(String email);

}

package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.PhuongXaEntity;

@Repository
public interface PhuongXaRepository extends JpaRepository<PhuongXaEntity, Long> {

    Optional<PhuongXaEntity> findOneByMaPhuongXa(long maPhuongXa);

    List<PhuongXaEntity> findPhuongXasByQuanHuyen_MaQuanHuyen(long maQuanHuyen);

}

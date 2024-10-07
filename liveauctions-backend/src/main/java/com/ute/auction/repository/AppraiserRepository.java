package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.AppraiserEntity;

@Repository
public interface AppraiserRepository extends JpaRepository<AppraiserEntity, Integer> {

        Optional<AppraiserEntity> findOneByEmail(String email);

        @Query(value = "SELECT ap.* FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%"
                        +
                        " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", countQuery = "SELECT COUNT(ap.id) FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%"
                                        +
                                        " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", nativeQuery = true)
        Page<AppraiserEntity> searchAppraiser(String keyword, Pageable pageable);

        @Query(value = "SELECT ap.* FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%"
                        +
                        " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", nativeQuery = true)
        List<AppraiserEntity> existsAppraiser(String keyword);

        @Query(value = "SELECT ap.* FROM appraiser ap ORDER BY ap.name ASC", countQuery = "SELECT COUNT(ap.id) FROM appraiser ap", nativeQuery = true)
        Page<AppraiserEntity> sortedAscByName(Pageable pageable);

        @Query(value = "SELECT ap.* FROM appraiser ap ORDER BY ap.name DESC", countQuery = "SELECT COUNT(ap.id) FROM appraiser ap", nativeQuery = true)
        Page<AppraiserEntity> sortedDescByName(Pageable pageable);

        @Query(value = "SELECT ap.* FROM appraiser ap ORDER BY ap.dob ASC", countQuery = "SELECT COUNT(ap.id) FROM appraiser ap", nativeQuery = true)
        Page<AppraiserEntity> sortedAscByDoB(Pageable pageable);

        @Query(value = "SELECT ap.* FROM appraiser ap ORDER BY ap.dob DESC", countQuery = "SELECT COUNT(ap.id) FROM appraiser ap", nativeQuery = true)
        Page<AppraiserEntity> sortedDescByDoB(Pageable pageable);

}

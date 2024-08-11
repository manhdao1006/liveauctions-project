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
public interface AppraiserRepository extends JpaRepository<AppraiserEntity, Long> {

    Optional<AppraiserEntity> findOneByEmail(String email);

    @Query(value = "SELECT ap.* FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%" + 
                " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", 
            countQuery = "SELECT COUNT(ap.id) FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%" + 
                        " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", 
            nativeQuery = true)
    Page<AppraiserEntity> searchAppraiser(String keyword, Pageable pageable);
    
    @Query(value = "SELECT ap.* FROM appraiser ap WHERE ap.name like %?1% or ap.email like %?1% or ap.gender like %?1%" + 
                " or ap.phone_number like %?1% or ap.address like %?1% or ap.dob like %?1% or ap.description like %?1%", 
            nativeQuery = true)
    List<AppraiserEntity> existsAppraiser(String keyword);

}

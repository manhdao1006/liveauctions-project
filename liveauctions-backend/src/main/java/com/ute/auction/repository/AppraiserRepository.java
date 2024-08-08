package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.AppraiserEntity;

@Repository
public interface AppraiserRepository extends JpaRepository<AppraiserEntity, Long> {

    Optional<AppraiserEntity> findOneByEmail(String email);

    @Query(value = "SELECT app.* FROM appraiser app", nativeQuery = true)
    List<AppraiserEntity> getAllTest();
    
}

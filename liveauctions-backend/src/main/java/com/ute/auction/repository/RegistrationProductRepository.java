package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.RegistrationProductEntity;

@Repository
public interface RegistrationProductRepository extends JpaRepository<RegistrationProductEntity, Long> {

    @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.seller_id, rp.sub_category_id, rp.auction_format_id " + 
                    "FROM registration_product rp " + 
                    "WHERE rp.seller_id like ?1", 
            countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1",
            nativeQuery = true)
    Page<RegistrationProductEntity> findRegistrationProductsBySellerId(Long sellerId, Pageable pageable);
    
}

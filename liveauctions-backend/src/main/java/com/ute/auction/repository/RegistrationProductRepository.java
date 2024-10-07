package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.RegistrationProductEntity;

@Repository
public interface RegistrationProductRepository extends JpaRepository<RegistrationProductEntity, Integer> {

        @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.del_flag, rp.seller_id, rp.sub_category_id, rp.auction_format_id "
                        +
                        "FROM registration_product rp " +
                        "WHERE rp.seller_id like ?1", countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1", nativeQuery = true)
        Page<RegistrationProductEntity> findRegistrationProductsBySellerId(int sellerId, Pageable pageable);

        @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.del_flag, rp.seller_id, rp.sub_category_id, rp.auction_format_id "
                        +
                        "FROM registration_product rp " +
                        "WHERE rp.seller_id like ?1 " +
                        "ORDER BY rp.starting_price ASC", countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1", nativeQuery = true)
        Page<RegistrationProductEntity> sortedAscByStartingPrice(int sellerId, Pageable pageable);

        @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.del_flag, rp.seller_id, rp.sub_category_id, rp.auction_format_id "
                        +
                        "FROM registration_product rp " +
                        "WHERE rp.seller_id like ?1 " +
                        "ORDER BY rp.starting_price DESC", countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1", nativeQuery = true)
        Page<RegistrationProductEntity> sortedDescByStartingPrice(int sellerId, Pageable pageable);

        @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.del_flag, rp.seller_id, rp.sub_category_id, rp.auction_format_id "
                        +
                        "FROM registration_product rp " +
                        "WHERE rp.seller_id like ?1 " +
                        "ORDER BY rp.registration_date ASC", countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1", nativeQuery = true)
        Page<RegistrationProductEntity> sortedAscByRegistrationDate(int sellerId, Pageable pageable);

        @Query(value = "SELECT rp.id, rp.name, rp.starting_price, rp.status, rp.registration_date, rp.description, rp.del_flag, rp.seller_id, rp.sub_category_id, rp.auction_format_id "
                        +
                        "FROM registration_product rp " +
                        "WHERE rp.seller_id like ?1 " +
                        "ORDER BY rp.registration_date DESC", countQuery = "SELECT COUNT(rp.id) FROM registration_product rp WHERE rp.seller_id = ?1", nativeQuery = true)
        Page<RegistrationProductEntity> sortedDescByRegistrationDate(int sellerId, Pageable pageable);

}

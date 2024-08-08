package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "SELECT p.id, p.name, p.starting_price, p.status, p.min_price, p.max_price, p.description, p.seller_id, p.sub_category_id, p.auction_format_id, p.warehouse_id, p.appraiser_id, " + 
                    "ap.auction_id " + 
                    "FROM product p " + 
                    "LEFT JOIN warehouse w ON p.warehouse_id = w.id " + 
                    "LEFT JOIN auction_product ap ON p.id = ap.product_id " + 
                    "LEFT JOIN auction au ON ap.auction_id = au.id " + 
                    "LEFT JOIN images i ON p.id = i.product_id " + 
                    "LEFT JOIN appraiser a ON p.appraiser_id = a.id " + 
                    "WHERE p.seller_id like ?1", 
            countQuery = "SELECT COUNT(p.id) FROM product p WHERE p.seller_id like ?1", 
            nativeQuery = true)
    Page<ProductEntity> findProductsBySellerId(Long sellerId, Pageable pageable);
    
}

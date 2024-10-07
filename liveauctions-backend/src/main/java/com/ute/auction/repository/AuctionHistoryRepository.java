package com.ute.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.AuctionHistoryEntity;
import com.ute.auction.entity.impl.AuctionHistoryId;

@Repository
public interface AuctionHistoryRepository extends JpaRepository<AuctionHistoryEntity, AuctionHistoryId> {

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> findOrdersBySellerId(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.order_status like ?2 AND ah.status like 'done'", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.order_status like ?2 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> findOrdersByOrderStatus(int sellerId, String orderStatus, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.auctioned_price ASC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedAscByAuctionedPrice(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.auctioned_price DESC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedDescByAuctionedPrice(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.order_date ASC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedAscByOrderDate(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.order_date DESC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedDescByOrderDate(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.delivery_date ASC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedAscByDeliveryDate(int sellerId, Pageable pageable);

        @Query(value = "SELECT ah.auction_id, ah.buyer_id, ah.fee_id, ah.holiday_id, ah.product_id, ah.bid_time, ah.delivery_date, ah.status,"
                        +
                        " au.id, au.name, p.name, u.first_name, u.last_name, ah.auctioned_price, ah.order_date, ah.order_status"
                        +
                        " FROM auction_history ah" +
                        " JOIN auction au ON ah.auction_id = au.id" +
                        " JOIN product p ON ah.product_id = p.id" +
                        " JOIN buyer b ON ah.buyer_id = b.buyer_id" +
                        " JOIN fee f ON ah.fee_id = f.id" +
                        " JOIN holiday h ON ah.holiday_id = h.id" +
                        " JOIN seller s ON p.seller_id = s.seller_id" +
                        " JOIN [user] u ON b.buyer_id = u.id" +
                        " WHERE s.seller_id like ?1 AND ah.status like 'done'" +
                        " ORDER BY ah.delivery_date DESC", countQuery = "SELECT COUNT(ah.product_id) FROM auction_history ah"
                                        +
                                        " JOIN product p ON ah.product_id = p.id" +
                                        " JOIN seller s ON p.seller_id = s.seller_id" +
                                        " WHERE s.seller_id like ?1 AND ah.status like 'done'", nativeQuery = true)
        Page<AuctionHistoryEntity> sortedDescByDeliveryDate(int sellerId, Pageable pageable);

}

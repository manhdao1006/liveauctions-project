package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        @Query(value = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.password, u.phone_number, u.address, u.status, u.avatar, u.dob, u.gender, u.del_flag, u.city_id, "
                        +
                        "c.state_id " +
                        "FROM users u " +
                        "JOIN cities c ON u.city_id = c.city_id " +
                        "JOIN states st ON c.state_id = st.state_id " +
                        "JOIN sellers se ON u.user_id = se.seller_id " +
                        "WHERE u.user_id like ?1", nativeQuery = true)
        UserEntity findByUserId(int userId);

        // @Query("SELECT u FROM UserEntity u JOIN u.seller s WHERE u.email = ?1")
        // Optional<UserEntity> findByEmailOfSeller(String email);

        Optional<UserEntity> findByEmail(String email);

        Boolean existsByEmail(String email);

}

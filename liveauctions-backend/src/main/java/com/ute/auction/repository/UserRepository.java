package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, u.phone_number, u.address, u.status, u.avatar, u.dob, u.gender, u.city_id, u.role_id, " + 
                    "c.state_id " + 
                    "FROM [user] u " + 
                    "JOIN city c ON u.city_id = c.id " + 
                    "JOIN state st ON c.state_id = st.id " + 
                    "JOIN seller se ON u.id = se.seller_id " + 
                    "WHERE u.id like ?1", 
            nativeQuery = true)
    UserEntity findUserById(Long id);

    @Query(value = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, u.phone_number, u.address, u.status, u.avatar, u.dob, u.gender, u.city_id, u.role_id, " + 
                    "c.state_id " + 
                    "FROM [user] u " + 
                    "JOIN city c ON u.city_id = c.id " + 
                    "JOIN state st ON c.state_id = st.id " + 
                    "JOIN seller se ON u.id = se.seller_id " + 
                    "WHERE u.email like ?1", 
            nativeQuery = true)
    UserEntity findUserByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
    
}

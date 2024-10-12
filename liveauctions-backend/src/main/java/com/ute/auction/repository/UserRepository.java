package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        @Query("SELECT u FROM UserEntity u " +
                        "JOIN u.city c " +
                        "JOIN c.state st " +
                        "LEFT JOIN u.staff staff " +
                        "LEFT JOIN u.seller seller " +
                        "LEFT JOIN u.buyer buyer " +
                        "WHERE u.userId = ?1")
        UserEntity findByUserId(int userId);

        @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.roleId = ?1")
        List<UserEntity> findUsersByRole(int roleId);

        Optional<UserEntity> findByEmail(String email);

        Boolean existsByEmail(String email);

}

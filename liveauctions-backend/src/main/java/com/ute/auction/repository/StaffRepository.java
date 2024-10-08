package com.ute.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ute.auction.entity.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

}

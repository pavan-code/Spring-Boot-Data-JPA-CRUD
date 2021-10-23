package com.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}

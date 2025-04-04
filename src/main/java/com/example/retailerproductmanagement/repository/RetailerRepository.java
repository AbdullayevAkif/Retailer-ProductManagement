package com.example.retailerproductmanagement.repository;

import com.example.retailerproductmanagement.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {

}

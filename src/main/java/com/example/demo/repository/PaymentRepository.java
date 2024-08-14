package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.PaymentInfo;


public interface PaymentRepository extends JpaRepository<PaymentInfo,Integer>
{
    @Query("SELECT p FROM PaymentInfo p WHERE p.name=:name")
    public List<PaymentInfo> getPaymentsOfUser(@Param("name")String name);
}

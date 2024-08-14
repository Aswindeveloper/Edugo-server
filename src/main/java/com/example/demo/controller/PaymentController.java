package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PaymentInfo;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController 
{
    @Autowired
    PaymentService service;
    @PostMapping("/post")
    public void post(@RequestBody PaymentInfo info)
    {
        service.post(info);
    }
}

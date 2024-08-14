package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserInfoRepository;

@Service
public class AdminService {

    @Autowired
    private UserInfoRepository urepo;

    @Autowired
    private PaymentRepository prepo;

    @Autowired
    private OrdersRepository orepo;

    public void addAdmin(UserInfo obj) {
        obj.setRoles("ROLE_ADMIN");
        urepo.save(obj);
    }

    public void addUser(UserInfo obj) {
        urepo.save(obj);
    }

    public void changeStatus(int id) {
        Optional<Orders> orderOpt = orepo.findById(id);
        if (orderOpt.isPresent()) {
            Orders obj = orderOpt.get();
            obj.setStatus("Approved");
            orepo.save(obj);
        }
    }

    public void deleteUser(int id) {
        Optional<UserInfo> userOpt = urepo.findById(id);
        if (userOpt.isPresent()) {
            UserInfo obj = userOpt.get();
            urepo.delete(obj);
        }
    }

    public boolean updateUser(int id, UserInfo updatedUser) {
        Optional<UserInfo> userOpt = urepo.findById(id);
        if (userOpt.isPresent()) {
            UserInfo existingUser = userOpt.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // Update other fields as needed
            urepo.save(existingUser);
            return true;
        }
        return false;
    }

    public List<Orders> getOrders() {
        return orepo.findAll();
    }

    public List<UserInfo> getUsers() {
        return urepo.findAll();
    }
}

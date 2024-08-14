package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Orders;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.AdminService;

@RestController
@CrossOrigin(origins = "https://edugo-aswins.vercel.app")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @PostMapping("/post/admin")
    public ResponseEntity<String> addAdmin(@RequestBody UserInfo obj) {
        obj.setRoles("ROLE_ADMIN");
        service.addAdmin(obj);
        return ResponseEntity.ok("Admin added successfully");
    }

    @PostMapping("/post/user")
    public ResponseEntity<String> addUser(@RequestBody UserInfo obj) {
        service.addUser(obj);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/changestatus/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable int id) {
        service.changeStatus(id);
        return ResponseEntity.ok("Order approved");
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edituser/{id}")
    public ResponseEntity<String> editUser(@PathVariable int id, @RequestBody UserInfo updatedUser) {
        boolean success = service.updateUser(id, updatedUser);
        if (success) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/get/orders")
    public List<Orders> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/get/users")
    public List<UserInfo> getUsers() {
        return service.getUsers();
    }
}

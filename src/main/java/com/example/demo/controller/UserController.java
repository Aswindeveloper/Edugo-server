package com.example.demo.controller;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.Orders;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserInfoService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://edugo-aswins.vercel.app")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            UserInfo userInfo = service.getUserByUsername(authRequest.getUsername());
            if (userInfo.getRoles().equals(authRequest.getRole())) {
                String token = jwtService.generateToken(authRequest.getUsername());
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                throw new UsernameNotFoundException("Invalid role for the user!");
            }
        } else {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
    }
    @PostMapping("/order")
    public void order(@RequestBody Orders obj)
    {
        service.order(obj);
    }

}

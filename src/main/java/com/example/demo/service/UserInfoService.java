package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    OrdersRepository orepo;

    @Autowired
    private UserInfoRepository useriInfoRepository;

    public UserInfo getUserByUsername(String username) {
        return useriInfoRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
    public void order(Orders obj)
    {
        orepo.save(obj);
    }

}

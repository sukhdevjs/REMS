//package com.sukhdev.rems.services.utils;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class JwtService {
//
//    private final JwtUtils jwtUtils;
//
//    public JwtService(JwtUtils jwtUtils) {
//        this.jwtUtils = jwtUtils;
//    }
//
//    public String generateAdminToken(UserDetails adminUser) {
//        List<String> roles = List.of("ADMIN"); // Add ADMIN role
//        return jwtUtils.generateToken(adminUser, roles);
//    }
//}
//

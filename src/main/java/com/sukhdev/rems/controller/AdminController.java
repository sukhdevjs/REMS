package com.sukhdev.rems.controller;

import com.sukhdev.rems.dto.PropertyDto;
import com.sukhdev.rems.repository.PropertyRepository;
import com.sukhdev.rems.services.adminService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/property")
    public ResponseEntity<?> PostProperty(@ModelAttribute PropertyDto propertyDto){
        boolean success = adminService.postProperty(propertyDto);
        if (success)
           return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}

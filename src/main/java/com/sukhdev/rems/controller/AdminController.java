package com.sukhdev.rems.controller;

import com.sukhdev.rems.dto.PropertyDto;
import com.sukhdev.rems.repository.PropertyRepository;
import com.sukhdev.rems.services.adminService.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {

    private final AdminService adminService;


    @PostMapping(value = "/property", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postProperty(@ModelAttribute PropertyDto propertyDto){
        boolean success = adminService.postProperty(propertyDto);
        if (success)
           return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/list-properties")
    public ResponseEntity<List<PropertyDto>> getAllProperty(){
        List<PropertyDto> propertyDtoList  = adminService.getAllProperty();
        return ResponseEntity.ok(propertyDtoList);
    }

    @DeleteMapping("/property/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId){
        adminService.deleteProperty(propertyId);
        return ResponseEntity.noContent().build();
    }
}

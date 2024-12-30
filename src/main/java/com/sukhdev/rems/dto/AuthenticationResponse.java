package com.sukhdev.rems.dto;

import com.sukhdev.rems.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;


}

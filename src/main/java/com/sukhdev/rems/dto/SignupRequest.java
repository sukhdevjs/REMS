package com.sukhdev.rems.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {

    private String email;
    private String name;
    private String password;

}

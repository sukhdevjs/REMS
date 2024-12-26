package com.sukhdev.rems.services.auth;

import com.sukhdev.rems.dto.SignupRequest;
import com.sukhdev.rems.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);


    boolean hasCustomerWithEmail(String email);
}

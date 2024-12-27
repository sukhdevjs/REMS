package com.sukhdev.rems.services.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService  userDetailsService();

}

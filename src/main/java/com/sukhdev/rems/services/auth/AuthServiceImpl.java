package com.sukhdev.rems.services.auth;

import com.sukhdev.rems.dto.SignupRequest;
import com.sukhdev.rems.dto.UserDto;
import com.sukhdev.rems.entity.User;
import com.sukhdev.rems.enums.UserRole;
import com.sukhdev.rems.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null){
            User newAdmin = new User();
            newAdmin.setUserRole(UserRole.ADMIN);
//            newAdmin.setName("Admin");
            newAdmin.setEmail("admin@gmail.com");
            newAdmin.setPassword(new BCryptPasswordEncoder().encode("Password"));
            userRepository.save(newAdmin);
        }

    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole((UserRole.CUSTOMER));
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setEmail(createdUser.getEmail());
        userDto.setName(createdUser.getName());
        userDto.setUserRole(createdUser.getUserRole());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email){
    return userRepository.findFirstByEmail(email).isPresent();
    }
}

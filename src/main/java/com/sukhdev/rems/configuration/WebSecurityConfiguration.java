package com.sukhdev.rems.configuration;


import com.sukhdev.rems.enums.UserRole;
import com.sukhdev.rems.services.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private  final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request ->
                        request.requestMatchers("api/auth/**").permitAll().
//                                requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name()).
                                requestMatchers("/api/admin/**").permitAll().
                                requestMatchers("/api/auth/user/**").permitAll().
                                anyRequest().authenticated()).sessionManagement(manager ->
                        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authenticationProvider(authenticationProvider()).
                addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(request ->
//                    request
//                            .requestMatchers("/**").permitAll() // Allow access to all routes
//                            .requestMatchers("/api/admin/**").hasAuthority("ADMIN") // Restrict admin routes
//            )
//            .sessionManagement(manager ->
//                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .authenticationProvider(authenticationProvider())
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//}


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
////                .exceptionHandling(exception ->
////                        exception.authenticationEntryPoint(unauthorizedHandler))
//
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/users").permitAll()
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().permitAll());
//
//
//        http.authenticationProvider(authenticationProvider()); // Set the authentication provider
//
//
////        http.addFilterBefore(authenticationJwtTokenFilter(),
////                UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
}

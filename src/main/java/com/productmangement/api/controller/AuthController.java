package com.productmangement.api.controller;

import com.productmangement.api.dto.RegisterUserDTO;
import com.productmangement.api.dto.UserDTO;
import com.productmangement.api.enums.Role;
import com.productmangement.api.dto.ApiResponse;
import com.productmangement.api.model.EntityUser;
import com.productmangement.api.repository.UserRepository;
import com.productmangement.api.security.CustomUserDetailsService;
import com.productmangement.api.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication API", description = "Operations related to authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ApiResponse<String> authenticate(@RequestBody UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ApiResponse.success(jwt,"User registered successfully!");
    }

    @PostMapping("/register")
    public ApiResponse<EntityUser> register(@RequestBody RegisterUserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return ApiResponse.error("User already exists!");
        }
        EntityUser user = new EntityUser(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), Collections.singleton(Role.USER));
        return ApiResponse.success(userRepository.save(user),"User registered successfully!");
    }

}

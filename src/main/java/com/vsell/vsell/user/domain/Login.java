package com.vsell.vsell.user.domain;


import com.vsell.vsell.security.JwtProvider;

public class Login {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public Login(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public void login(String email, String password){

    }
}

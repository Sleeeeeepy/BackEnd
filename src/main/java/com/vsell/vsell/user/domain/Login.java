package com.vsell.vsell.user.domain;


import com.vsell.vsell.security.JwtProvider;
import com.vsell.vsell.security.dto.JwtTokenDto;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Login {
    private final VSellUserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Login(VSellUserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtTokenDto login(String email, String password) {
        VSellUser user = userRepository.findByEmail(email);

        if (user == null) {
            throw new CustomUserException(UserExceptionType.FAIL_LOGIN);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomUserException(UserExceptionType.FAIL_LOGIN);
        }

        return createToken(email);
    }

    private JwtTokenDto createToken(String email) {
        String accessToken = jwtProvider.createAccessToken(email);
        String refreshToken = jwtProvider.createRefreshToken(email);

        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setAccessToken(accessToken);
        jwtTokenDto.setRefreshToken(refreshToken);

        return jwtTokenDto;
    }
}

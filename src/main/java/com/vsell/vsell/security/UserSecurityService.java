package com.vsell.vsell.security;

import com.vsell.vsell.security.exception.CustomSecurityException;
import com.vsell.vsell.security.exception.SecurityExceptionType;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.VSellUserRepository;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    private final VSellUserRepository vSellUserRepository;

    public UserSecurityService(VSellUserRepository vSellUserRepository) {
        this.vSellUserRepository = vSellUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VSellUser user = null;
        try {
            user = vSellUserRepository.findByEmail(username);
        } catch (CustomUserException ex) {
            throw new CustomSecurityException(SecurityExceptionType.NOT_EXIST_EMAIL);
        }

        return new User(user.getEmail(), user.getPassword(), user.getUserRoles());
    }

    public Optional<String> getLoginUser() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Optional<String> email = Optional.empty();

        if (user instanceof AnonymousAuthenticationToken) {
            return email;
        }

        email = Optional.of(user.getName());

        return email;
    }

}
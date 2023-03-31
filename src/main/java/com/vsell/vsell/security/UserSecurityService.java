package com.vsell.vsell.security;

import com.vsell.vsell.user.domain.UserRole;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.VSellUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class UserSecurityService implements UserDetailsService {

    private final VSellUserRepository vSellUserRepository;

    public UserSecurityService(VSellUserRepository vSellUserRepository) {
        this.vSellUserRepository = vSellUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VSellUser user = vSellUserRepository.findByEmail(username);

        return new User(user.getEmail(), user.getPassword(), user.getUserRoles());
    }
}

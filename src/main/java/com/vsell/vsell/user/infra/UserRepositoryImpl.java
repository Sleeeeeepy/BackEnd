package com.vsell.vsell.user.infra;

import com.vsell.vsell.user.domain.User;
import com.vsell.vsell.user.domain.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public User findByNickName(String nickName) {
        return jpaUserRepository.findByNickName(nickName);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(user);
    }
}

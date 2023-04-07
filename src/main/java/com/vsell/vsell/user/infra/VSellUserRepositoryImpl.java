package com.vsell.vsell.user.infra;

import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.VSellUserRepository;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import org.springframework.stereotype.Component;

@Component
public class VSellUserRepositoryImpl implements VSellUserRepository {
    private final JpaUserRepository jpaUserRepository;

    public VSellUserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public VSellUser findByEmail(String email) {
        VSellUser vSellUser = jpaUserRepository.findByEmail(email);

        if (vSellUser == null) {
            throw new CustomUserException(UserExceptionType.NOT_EXIST_EMAIL);
        }
        return vSellUser;
    }

    @Override
    public VSellUser findByNickName(String nickName) {
        VSellUser vSellUser = jpaUserRepository.findByNickName(nickName);

        if (vSellUser == null) {
            throw new CustomUserException(UserExceptionType.NOT_EXIST_NICKNAME);
        }

        return vSellUser;
    }

    @Override
    public void save(VSellUser user) {
        jpaUserRepository.save(user);
    }
}

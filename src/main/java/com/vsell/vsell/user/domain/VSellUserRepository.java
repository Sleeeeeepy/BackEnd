package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;

public interface VSellUserRepository {
    VSellUser findByEmail(String email) throws CustomUserException;

    VSellUser findByNickName(String nickName) throws CustomUserException;

    void save(VSellUser user);
}

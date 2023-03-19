package com.vsell.vsell.user.domain;

public interface UserRepository {
    User findByEmail(String email);
    User findByNickName(String nickName);
    void save(User user);
}

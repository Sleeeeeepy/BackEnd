package com.vsell.vsell.user.domain;

public interface VSellUserRepository {
    VSellUser findByEmail(String email);
    VSellUser findByNickName(String nickName);
    void save(VSellUser user);
}

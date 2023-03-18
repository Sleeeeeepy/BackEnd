package com.vsell.vsell.user.domain;

public interface UserRepository {
    public User findByEmail(String email);
    public void save(User user);
}

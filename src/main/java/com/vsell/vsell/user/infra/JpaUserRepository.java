package com.vsell.vsell.user.infra;

import com.vsell.vsell.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByNickName(String nickName);
}

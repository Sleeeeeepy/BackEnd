package com.vsell.vsell.user.infra;

import com.vsell.vsell.user.domain.VSellUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<VSellUser, Long> {
    VSellUser findByEmail(String email);

    VSellUser findByNickName(String nickName);
}

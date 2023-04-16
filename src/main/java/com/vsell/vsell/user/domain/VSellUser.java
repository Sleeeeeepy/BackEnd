package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VSellUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(length = 10, unique = true, nullable = false)
    private String nickName;

    @Column(nullable = false)
    private Instant birthDate;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRoles;

    @JoinColumn(name = "profile_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @Builder
    public VSellUser(String name, String email, String password, String nickName, Instant birthDate, PasswordEncoder passwordEncoder) {
        assertValidName(name);
        assertValidEmail(email);
        assertValidPassword(password);
        assertValidNickName(nickName);
        assertValidBirthDate(birthDate);

        this.name = name;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.profile = new Profile();

        //TODO: admin은 직접 db쿼리로 수정하도록 할 것인지 상의 필요.
        this.userRoles = EnumSet.noneOf(UserRole.class);
        this.userRoles.add(UserRole.ROLE_USER);
    }

    private void assertValidName(String name) {
        if (name == null) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_NAME);
        }
        if (name.length() > 255) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_NAME);
        }
    }

    private void assertValidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (email == null) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
        if (!Pattern.compile(regexPattern).matcher(email).matches()) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
        if (email.length() > 50) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
    }

    private void assertValidPassword(String password) {
        if (password == null) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_PASSWORD);
        }
        if (password.length() > 20) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_PASSWORD);
        }
    }

    private void assertValidNickName(String nickName) {
        if (nickName == null) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_NICKNAME);
        }
        if (nickName.length() > 10) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_NICKNAME);
        }
    }

    private void assertValidBirthDate(Instant birthDate) {
        if (birthDate == null) {
            throw new CustomUserException(UserExceptionType.INVALID_USER_BIRTHDATE);
        }

        if(Instant.now().isBefore(birthDate)){
            throw new CustomUserException(UserExceptionType.INVALID_USER_BIRTHDATE);
        }

    }

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        assertValidPassword(password);
        this.password = passwordEncoder.encode(password);
    }

    public void setNickName(String nickName) {
        assertValidNickName(nickName);
        this.nickName = nickName;
    }

}

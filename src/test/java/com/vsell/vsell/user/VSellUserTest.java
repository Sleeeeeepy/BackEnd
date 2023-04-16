package com.vsell.vsell.user;


import com.vsell.vsell.security.exception.CustomSecurityException;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class VSellUserTest {

    private final String validEmail = "goh1211@naver.com";
    private final String validPassword = "1234";
    private final String validNickName = "하용권";
    private final String validName = "권용하";

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private final Instant validBirthDate = LocalDate.parse("1998-12-11", dtf).atStartOfDay(ZoneOffset.UTC).toInstant();


    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @DisplayName("이메일 형태가 아닌 경우 에러 발생")
    @Test
    public void invalidEmailTest(){

        assertThrows(CustomUserException.class, () -> new VSellUser(validName, "12455", validPassword, validNickName, validBirthDate, passwordEncoder));
    }

    @DisplayName("닉네임 길이가 너무 긴 경우(10글자 초과) 에러 발생")
    @Test
    public void longNicknameTest(){
        String invalidNickname1 = "가나다라가나다라가나다";
        assertThrows(CustomUserException.class, () -> new VSellUser(validName, validEmail, validPassword, invalidNickname1, validBirthDate, passwordEncoder));

        String invalidNickname2 = "가나다라가나다라가나2";
        assertThrows(CustomUserException.class, () -> new VSellUser(validName, validEmail, validPassword, invalidNickname2, validBirthDate, passwordEncoder));

        String invalidNickname3 = "abcdabcdabc";
        assertThrows(CustomUserException.class, () -> new VSellUser(validName, validEmail, validPassword, invalidNickname3, validBirthDate, passwordEncoder));
    }

    @DisplayName("이름 길이가 너무 긴 경우(255) 에러발생")
    @Test
    public void longNameTest(){
        String invalidName1 = StringUtils.repeat('a', 256);
        assertThrows(CustomUserException.class, () -> new VSellUser(invalidName1, validEmail, validPassword, validNickName, validBirthDate, passwordEncoder));

        String invalidName2 = StringUtils.repeat('가', 256);
        assertThrows(CustomUserException.class, () -> new VSellUser(invalidName2, validEmail, validPassword, validNickName, validBirthDate, passwordEncoder));


    }

    @DisplayName("비밀번호가 너무 긴 경우(20) 에러발생")
    @Test
    public void longPasswordTest(){
        String invalidPassword = StringUtils.repeat('a', 21);
        assertThrows(CustomUserException.class, () -> new VSellUser(validName, validEmail, validPassword, validNickName, validBirthDate, passwordEncoder));

    }



}

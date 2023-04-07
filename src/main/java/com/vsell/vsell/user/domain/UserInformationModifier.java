package com.vsell.vsell.user.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserInformationModifier {

    private final VSellUserRepository vSellUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileHandler profileHandler;

    public UserInformationModifier(VSellUserRepository vSellUserRepository, PasswordEncoder passwordEncoder, ProfileHandler profileHandler) {
        this.vSellUserRepository = vSellUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileHandler = profileHandler;
    }

    public void changePassword(VSellUser user, String password) {
        user.setPassword(password, passwordEncoder);

        vSellUserRepository.save(user);
    }

    public void changeNickName(VSellUser user, String nickName) {
        user.setNickName(nickName);

        vSellUserRepository.save(user);
    }

    public void changeProfile(VSellUser user, MultipartFile image) {
        profileHandler.saveProfile(image, user);
    }
}

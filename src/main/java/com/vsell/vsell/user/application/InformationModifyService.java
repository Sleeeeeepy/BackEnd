package com.vsell.vsell.user.application;

import com.vsell.vsell.user.domain.UserInformationModifier;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.VSellUserRepository;
import com.vsell.vsell.user.dto.InformationModifyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InformationModifyService {

    private final VSellUserRepository vSellUserRepository;
    private final UserInformationModifier userInformationModifier;


    public InformationModifyService(VSellUserRepository vSellUserRepository, UserInformationModifier userInformationModifier) {
        this.vSellUserRepository = vSellUserRepository;
        this.userInformationModifier = userInformationModifier;
    }

    @Transactional
    public VSellUser changeProfile(MultipartFile profile, String email) {
        VSellUser user = vSellUserRepository.findByEmail(email);

        userInformationModifier.changeProfile(user, profile);

        vSellUserRepository.save(user);

        return user;
    }

    @Transactional
    public VSellUser changeUserInformation(InformationModifyDto informationModifyDto) {
        VSellUser user = vSellUserRepository.findByEmail(informationModifyDto.getEmail());

        changeUserPassword(user, informationModifyDto.getChangePassword());
        changeUserNickName(user, informationModifyDto.getChangeNickname());

        vSellUserRepository.save(user);

        return user;
    }

    private void changeUserPassword(VSellUser user, String password) {
        userInformationModifier.changePassword(user, password);
    }

    private void changeUserNickName(VSellUser user, String nickName) {
        userInformationModifier.changeNickName(user, nickName);
    }
}

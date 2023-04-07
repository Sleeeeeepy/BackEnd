package com.vsell.vsell.user.application;

import com.vsell.vsell.user.domain.ProfileHandler;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.VSellUserRepository;
import com.vsell.vsell.user.dto.ProfileDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ProfileHandlerService {
    private final ProfileHandler profileHandler;

    private final VSellUserRepository vSellUserRepository;

    public ProfileHandlerService(ProfileHandler profileHandler, VSellUserRepository vSellUserRepository) {
        this.profileHandler = profileHandler;
        this.vSellUserRepository = vSellUserRepository;
    }

    public ProfileDto getProfile(String email){
        VSellUser user = vSellUserRepository.findByEmail(email);

        String path = profileHandler.getProfilePath(user);

        return profileHandler.getProfile(path);
    }
}

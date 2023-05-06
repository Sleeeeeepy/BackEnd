package com.vsell.vsell.user.infra;

import com.vsell.vsell.user.domain.ProfileHandler;
import com.vsell.vsell.user.domain.ProfileType;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import com.vsell.vsell.user.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ProfileHandlerImpl implements ProfileHandler {

    @Value("${vsell.user.profile.path}")
    private String defaultProfilePath;

    @Override
    public void saveProfile(MultipartFile profile, VSellUser user) {
        String profileExtension = getExtensionFromFile(profile);
        StringBuilder profilePath = new StringBuilder(defaultProfilePath);
        profilePath.append(user.getEmail()).append('.').append(profileExtension);

        try {
            profile.transferTo(new File(profilePath.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String prevPath = user.getProfile().getProfilePath();

        if (prevPath != null && !prevPath.equals(profilePath.toString())) {

            File prevProfile = new File(prevPath);
            if (prevProfile.exists()) {
                prevProfile.delete();
            }
        }
        user.getProfile().setProfilePath(profilePath.toString());
    }

    @Override
    public String getProfilePath(VSellUser user) {
        if (user.getProfile().getProfilePath() == null) {
            throw new CustomUserException(UserExceptionType.NOT_EXIST_PROFILE);
        }
        return user.getProfile().getProfilePath();
    }

    @Override
    public ProfileDto getProfile(String path) {
        InputStream profile = null;

        try {
            profile = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ProfileDto result = new ProfileDto();
        result.setProfile(new InputStreamResource(profile));
        result.setProfileType(getExtensionFromPath(path));

        return result;
    }

    private String getExtensionFromFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    private ProfileType getExtensionFromPath(String path) {
        StringBuilder extension = new StringBuilder(path.substring(path.lastIndexOf(".") + 1));

        return ProfileType.findByValue(extension.toString());
    }
}

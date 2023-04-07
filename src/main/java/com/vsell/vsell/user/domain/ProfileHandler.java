package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.dto.ProfileDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ProfileHandler {
    void saveProfile(MultipartFile profile, VSellUser user);
    String getProfilePath(VSellUser user);
    ProfileDto getProfile(String path);
}

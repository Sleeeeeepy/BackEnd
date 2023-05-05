package com.vsell.vsell.auction.domain;

import com.vsell.vsell.auction.dto.AvatarDto;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarHandler {
    String saveAvatar(MultipartFile avatar);

    AvatarDto getAvatar(String avatarName);
}

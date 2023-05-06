package com.vsell.vsell.auction.infra;

import com.vsell.vsell.auction.domain.AvatarHandler;
import com.vsell.vsell.auction.domain.AvatarType;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import com.vsell.vsell.auction.dto.AvatarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Component
public class AvatarHandlerImpl implements AvatarHandler {

    @Value("${vsell.auction.avatar.path}")
    private String DEFAULT_PATH;

    @Override
    public String saveAvatar(MultipartFile avatar) {
        String avatarPath = UUID.randomUUID().toString();
        avatarPath = DEFAULT_PATH + "/" + avatarPath + "." + getExtension(avatar.getOriginalFilename());

        File file = new File(avatarPath);

        if (file.exists()) {
            throw new AuctionException(AuctionExceptionType.EXIST_FILE);
        }

        try {
            avatar.transferTo(new File(avatarPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return avatarPath;
    }

    @Override
    public AvatarDto getAvatar(String avatarName) {
        AvatarDto avatarDto = new AvatarDto();

        String avatarPath = DEFAULT_PATH + avatarName;
        AvatarType avatarType = AvatarType.findByFileExtension(getExtension(avatarPath));

        InputStream avatar;
        try {
            avatar = new FileInputStream(avatarPath);
        } catch (FileNotFoundException e) {
            throw new AuctionException(AuctionExceptionType.NOT_EXIST_FILE);
        }

        avatarDto.setAvatarType(avatarType);
        avatarDto.setAvatar(new InputStreamResource(avatar));

        return avatarDto;
    }

    private String getExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        return AvatarType.findByFileExtension(extension).getFileExtension();
    }


}

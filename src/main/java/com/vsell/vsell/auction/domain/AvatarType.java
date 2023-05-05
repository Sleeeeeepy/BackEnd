package com.vsell.vsell.auction.domain;

import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import lombok.Getter;

@Getter
public enum AvatarType {
    Z7("7z", "application/zip"),
    ZIP("zip", "application/zip");

    private final String fileExtension;
    private final String mediaType;

    AvatarType(String fileExtension, String mediaType) {
        this.fileExtension = fileExtension;
        this.mediaType = mediaType;
    }

    static public AvatarType findByFileExtension(String fileExtension) {
        for (AvatarType avatarType : AvatarType.values()) {
            if (avatarType.getFileExtension().equals(fileExtension.toLowerCase())) {
                return avatarType;
            }
        }

        throw new AuctionException(AuctionExceptionType.INVALID_AVATAR_TYPE);
    }
}

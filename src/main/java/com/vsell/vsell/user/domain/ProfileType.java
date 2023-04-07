package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public enum ProfileType {
    JPG(MediaType.IMAGE_JPEG),
    JPEG(MediaType.IMAGE_JPEG),
    PNG(MediaType.IMAGE_PNG);

    private final MediaType mediaType;


    ProfileType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    static public ProfileType findByValue(String value) {
        for (ProfileType type : ProfileType.values()) {
            if (type.name().toLowerCase().equals(value.toLowerCase())) {
                return type;
            }
        }
        throw new CustomUserException(UserExceptionType.INVALID_PROFILE_TYPE);
    }
}

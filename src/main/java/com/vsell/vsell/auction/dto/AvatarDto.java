package com.vsell.vsell.auction.dto;

import com.vsell.vsell.auction.domain.AvatarType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
public class AvatarDto {
    private AvatarType avatarType;
    private InputStreamResource avatar;
}

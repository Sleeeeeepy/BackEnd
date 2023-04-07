package com.vsell.vsell.user.dto;


import com.vsell.vsell.user.domain.ProfileType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
public class ProfileDto {
    private InputStreamResource profile;
    private ProfileType profileType;
}

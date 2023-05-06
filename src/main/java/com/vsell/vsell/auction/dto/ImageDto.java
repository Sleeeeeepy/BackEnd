package com.vsell.vsell.auction.dto;

import com.vsell.vsell.auction.domain.ImageType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
public class ImageDto {
    private InputStreamResource image;
    private ImageType imgType;
}

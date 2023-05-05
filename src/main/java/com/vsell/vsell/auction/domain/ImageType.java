package com.vsell.vsell.auction.domain;

import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public enum ImageType {
    JPG(MediaType.IMAGE_JPEG),
    JPEG(MediaType.IMAGE_JPEG),
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF);


    private final MediaType mediaType;


    ImageType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    static public ImageType findByValue(String value) {
        for (ImageType type : ImageType.values()) {
            if (type.name().toLowerCase().equals(value.toLowerCase())) {
                return type;
            }
        }
        throw new AuctionException(AuctionExceptionType.INVALID_CONTENT_IMG);
    }
}

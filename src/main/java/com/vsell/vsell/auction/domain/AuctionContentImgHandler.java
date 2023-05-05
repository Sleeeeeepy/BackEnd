package com.vsell.vsell.auction.domain;

import com.vsell.vsell.auction.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface AuctionContentImgHandler {
    void saveImg(String imgName, MultipartFile img);

    ImageDto getImg(String imgName);
}

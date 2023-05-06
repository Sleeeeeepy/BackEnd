package com.vsell.vsell.auction.infra;

import com.vsell.vsell.auction.domain.AuctionContentImgHandler;
import com.vsell.vsell.auction.domain.ImageType;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import com.vsell.vsell.auction.dto.ImageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class AuctionContentImgHandlerImpl implements AuctionContentImgHandler {
    @Value("${vsell.auction.img.path}")
    private String defaultContentImgPath;

    @Override
    public void saveImg(String imgName, MultipartFile img) {
        String imgPath = defaultContentImgPath + imgName;
        File file = new File(imgPath);
        if (file.exists()) {
            throw new AuctionException(AuctionExceptionType.EXIST_FILE);
        }

        try {
            img.transferTo(new File(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageDto getImg(String imgName) {
        ImageDto imageDto = new ImageDto();

        String imgPath = defaultContentImgPath + imgName;
        imageDto.setImgType(getExtensionFromPath(imgPath));

        InputStream img;
        try {
            img = new FileInputStream(imgPath);
        } catch (FileNotFoundException e) {
            throw new AuctionException(AuctionExceptionType.NOT_EXIST_FILE);
        }

        imageDto.setImage(new InputStreamResource(img));

        return imageDto;
    }

    private ImageType getExtensionFromPath(String path) {
        StringBuilder extension = new StringBuilder(path.substring(path.lastIndexOf(".") + 1));

        return ImageType.findByValue(extension.toString());
    }
}

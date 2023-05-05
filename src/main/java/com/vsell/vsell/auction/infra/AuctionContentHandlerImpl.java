package com.vsell.vsell.auction.infra;


import com.vsell.vsell.auction.domain.AuctionContentHandler;
import com.vsell.vsell.auction.domain.AuctionContentImgHandler;
import com.vsell.vsell.auction.domain.ImageType;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@Component
public class AuctionContentHandlerImpl implements AuctionContentHandler {

    private final AuctionContentImgHandler auctionContentImgHandler;

    @Value("${vsell.server.url}")
    private String BASE_URL;

    public AuctionContentHandlerImpl(AuctionContentImgHandler auctionContentImgHandler) {
        this.auctionContentImgHandler = auctionContentImgHandler;
    }

    @Override
    public String processContent(String content, List<MultipartFile> files) {
        assertNotXSS(content);

        Document doc = Jsoup.parse(content);
        Elements imgs = doc.getElementsByTag("img");

        if (imgs.size() != files.size()) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_CONTENT);
        }



        for (int i = 0; i < files.size(); i++) {
            String fileName = files.get(i).getOriginalFilename();
            String fileExtension = getImgExtension(fileName);
            Element img = imgs.get(i);

            StringBuilder ret = new StringBuilder(UUID.randomUUID().toString());
            ret.append(".").append(fileExtension);

            auctionContentImgHandler.saveImg(ret.toString(), files.get(i));
            img.attr("src", ret.toString());
        }

        return doc.html();
    }

    private String getImgExtension(String fileName) {
        String ret = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        return ImageType.findByValue(ret).name().toLowerCase();
    }

    private void assertNotXSS(String content) {
        String tar = Jsoup.clean(content, BASE_URL, Safelist.relaxed().preserveRelativeLinks(true));

        if (!tar.equals(content)) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_CONTENT);
        }
    }


}
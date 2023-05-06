package com.vsell.vsell.auction.domain;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuctionContentHandler {
    String processContent(String content, List<MultipartFile> files);
}

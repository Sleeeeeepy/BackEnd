package com.vsell.vsell.auction.domain;

import com.vsell.vsell.auction.dto.AuctionRegisterDto;
import com.vsell.vsell.user.domain.VSellUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class AuctionRegistration {
    private final AuctionRepository auctionRepository;
    private final VSellUserRepository vSellUserRepository;
    private final AuctionContentHandler auctionContentHandler;
    private final AvatarHandler avatarHandler;

    public AuctionRegistration(AuctionRepository auctionRepository, VSellUserRepository vSellUserRepository, AuctionContentHandler auctionContentHandler, AvatarHandler avatarHandler) {
        this.auctionRepository = auctionRepository;
        this.vSellUserRepository = vSellUserRepository;
        this.auctionContentHandler = auctionContentHandler;
        this.avatarHandler = avatarHandler;
    }

    public long registerAuction(AuctionRegisterDto auctionDto, List<MultipartFile> imgs, MultipartFile avatar) {
        Auction auction = new Auction.AuctionBuilder()
                .vSellUserRepository(vSellUserRepository)
                .contentHandler(auctionContentHandler)
                .avatarHandler(avatarHandler)
                .contentImgs(imgs)
                .owner(auctionDto.getOwner())
                .title(auctionDto.getTitle())
                .content(auctionDto.getContent())
                .startsAt(auctionDto.getStartsAt())
                .endsAt(auctionDto.getEndsAt())
                .startingPrice(auctionDto.getStartingPrice())
                .build();


        auctionRepository.save(auction);

        return auction.getId();
    }
}

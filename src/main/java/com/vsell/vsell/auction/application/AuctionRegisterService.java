package com.vsell.vsell.auction.application;

import com.vsell.vsell.auction.domain.AuctionRegistration;
import com.vsell.vsell.auction.domain.AuctionRepository;
import com.vsell.vsell.auction.dto.AuctionRegisterDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AuctionRegisterService {
    public final AuctionRepository auctionRepository;
    public final AuctionRegistration auctionRegistration;

    public AuctionRegisterService(AuctionRepository auctionRepository, AuctionRegistration auctionRegistration) {
        this.auctionRepository = auctionRepository;
        this.auctionRegistration = auctionRegistration;
    }

    public long registerAuction(MultipartFile avatar, List<MultipartFile> imgs, AuctionRegisterDto auctionRegisterDto) {
        return auctionRegistration.registerAuction(auctionRegisterDto, imgs, avatar);
    }
}

package com.vsell.vsell.auction.presentation;

import com.vsell.vsell.auction.application.AuctionRegisterService;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import com.vsell.vsell.auction.dto.AuctionRegisterDto;
import com.vsell.vsell.auction.dto.AuctionRegisterResponseDto;
import com.vsell.vsell.security.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionRegisterController {
    private final UserSecurityService userSecurityService;
    private final AuctionRegisterService auctionRegisterService;

    public AuctionRegisterController(UserSecurityService userSecurityService, AuctionRegisterService auctionRegisterService) {
        this.userSecurityService = userSecurityService;
        this.auctionRegisterService = auctionRegisterService;
    }

    @PostMapping
    public ResponseEntity<AuctionRegisterResponseDto> registerAuction(@RequestPart("avatar") MultipartFile avatar, @RequestPart("images") List<MultipartFile> images, @RequestPart AuctionRegisterDto auctionRegisterDto) {
        String userEmail = userSecurityService.getLoginUser().orElseThrow(() -> new AuctionException(AuctionExceptionType.FORBIDDEN));

        if (!userEmail.equals(auctionRegisterDto.getOwner())) {
            throw new AuctionException(AuctionExceptionType.FORBIDDEN);
        }

        long auctionId = auctionRegisterService.registerAuction(avatar, images, auctionRegisterDto);
        AuctionRegisterResponseDto auctionRegisterResponseDto = new AuctionRegisterResponseDto();
        auctionRegisterResponseDto.setAuctionId(auctionId);

        return new ResponseEntity<>(auctionRegisterResponseDto, HttpStatus.OK);
    }


}

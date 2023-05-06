package com.vsell.vsell.auction.domain;


import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import com.vsell.vsell.user.domain.VSellUserRepository;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Auction {

    static private final int UNIT_BID = 10000;

    //4일
    static private final int START_LIMIT_SEC = 345600;

    //시작 시간 + 7일
    static private final int END_LIMIT_SEC = 604800;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String owner;

    @Column
    private String avatarPath;

    @Column
    private int startingPrice;

    @Column
    private String bidder;

    @Column
    private int bidPrice;

    @Column
    private Instant bidsAt;

    @Column
    private Instant startsAt;

    @Column
    private Instant endsAt;

//    @Column
//    private List<QnA> qnas;

    @Builder
    public Auction(String title, String content, String owner, MultipartFile avatar, int startingPrice, Instant startsAt, Instant endsAt, AuctionContentHandler contentHandler, List<MultipartFile> contentImgs, VSellUserRepository vSellUserRepository, AvatarHandler avatarHandler) {
        assertValidTitle(title);
        assertValidOwner(owner, vSellUserRepository);
        assertValidStartingPrice(startingPrice);
        assertValidStartsAt(startsAt);
        assertValidEndsAt(startsAt, endsAt);

        this.title = title;
        this.owner = owner;
        this.startingPrice = startingPrice;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.content = contentHandler.processContent(content, contentImgs);
        this.avatarPath = avatarHandler.saveAvatar(avatar);
    }

    private void assertValidTitle(String title) {
        if (title == null) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_TITLE);
        }
        if (title.length() > 255) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_TITLE);
        }
    }

    private void assertValidBidPrice(int bidPrice) {
        if (bidPrice <= 0) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_BID);
        }
        if (bidPrice % UNIT_BID != 0) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_BID);
        }
        if (bidPrice <= this.bidPrice || bidPrice < this.startingPrice) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_BID);
        }
    }

    private void assertValidStartingPrice(int startingPrice) {
        if (startingPrice < UNIT_BID) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_BID);
        }
        if (startingPrice % UNIT_BID != 0) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_BID);
        }
    }

    private void assertValidOwner(String owner, VSellUserRepository vSellUserRepository) {
        vSellUserRepository.findByEmail(owner);
    }

    private void assertValidStartsAt(Instant startsAt) {
        if (startsAt == null) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_START);
        }

        Instant nowTime = Instant.now();

        if (nowTime.plusSeconds(START_LIMIT_SEC).isBefore(startsAt)) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_START);
        }
    }

    private void assertValidEndsAt(Instant startsAt, Instant endsAt) {
        if (startsAt == null || endsAt == null) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_START);
        }

        if (startsAt.plusSeconds(END_LIMIT_SEC).isBefore(endsAt)) {
            throw new AuctionException(AuctionExceptionType.INVALID_AUCTION_END);
        }
    }


    //TODO: 수정 필요.
    public void changeBidPrice(String bidder, int bidPrice, VSellUserRepository vSellUserRepository) {
        Instant changeTime = Instant.now();
        if (changeTime.isBefore(startsAt) || changeTime.isAfter(endsAt)) {
            throw new AuctionException(AuctionExceptionType.INVALID_BID_TIME);
        }

        vSellUserRepository.findByEmail(bidder);
        if (bidPrice <= this.bidPrice) {
            throw new AuctionException(AuctionExceptionType.INVALID_BID_PRICE);
        }

        assertValidBidPrice(bidPrice);

        startsAt = changeTime;
        this.bidder = bidder;
        this.bidPrice = bidPrice;
        this.bidsAt = changeTime;
    }


}
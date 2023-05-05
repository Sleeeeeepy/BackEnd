package com.vsell.vsell.auction.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuctionRegisterDto {

    private String title;
    private String content;
    private String owner;

    private int startingPrice;

    private Instant startsAt;
    private Instant endsAt;
}

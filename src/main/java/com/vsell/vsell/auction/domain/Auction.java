package com.vsell.vsell.auction.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Getter
public class Auction {
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
    private String filePath;

    @Column
    private int startingPrice;

    @Column
    private int winningBidPrice;


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


}
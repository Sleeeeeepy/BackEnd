package com.vsell.vsell.auction.domain;

public interface AuctionRepository {
    void save(Auction auction);
    Auction findById(long id);
}

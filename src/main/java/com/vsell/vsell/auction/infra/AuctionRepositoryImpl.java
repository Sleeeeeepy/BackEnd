package com.vsell.vsell.auction.infra;

import com.vsell.vsell.auction.domain.Auction;
import com.vsell.vsell.auction.domain.AuctionRepository;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import com.vsell.vsell.auction.domain.exception.AuctionExceptionType;
import org.springframework.stereotype.Component;

@Component
public class AuctionRepositoryImpl implements AuctionRepository {

    private final JpaAuctionRepository jpaAuctionRepository;

    public AuctionRepositoryImpl(JpaAuctionRepository jpaAuctionRepository) {
        this.jpaAuctionRepository = jpaAuctionRepository;
    }

    @Override
    public void save(Auction auction) {
        jpaAuctionRepository.save(auction);
    }

    @Override
    public Auction findById(long id) {
        return jpaAuctionRepository.findById(id).orElseThrow(()-> new AuctionException(AuctionExceptionType.NOT_EXIST_AUCTION));
    }
}

package com.vsell.vsell.auction.infra;

import com.vsell.vsell.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAuctionRepository extends JpaRepository<Auction, Long> {
    Optional<Auction> findById(long id);
}

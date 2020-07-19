package com.unkapps.leilao.repository;


import com.unkapps.leilao.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}

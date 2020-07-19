package com.unkapps.leilao.repository;


import com.unkapps.leilao.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}

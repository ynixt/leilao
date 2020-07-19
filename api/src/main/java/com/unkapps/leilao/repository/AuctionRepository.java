package com.unkapps.leilao.repository;


import com.unkapps.leilao.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    @Query("select auction from Auction auction" +
            " join fetch auction.userResponsible responsible" +
            " where auction.id = :id")
    public Optional<Auction> getByIdFetchResponsible(@Param("id") Long id);

    public Optional<Auction> getById(@Param("id") Long id);

    @Query(value = "from Auction auction" +
            " join fetch auction.userResponsible responsible",
            countQuery = "from Auction auction")
    public Page<Auction> list(Pageable pageable);
}

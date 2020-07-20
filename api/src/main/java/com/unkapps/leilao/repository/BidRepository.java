package com.unkapps.leilao.repository;


import com.unkapps.leilao.domain.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;

public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query(
            value = "from Bid bid" +
                    " join fetch bid.madeByUser" +
                    " where bid.auction.id = :oidAuction",
            countQuery = "from Bid bid" +
                    " where bid.auction.id = :oidAuction"
    )
    Page<Bid> list(Pageable page, @NotNull @Param("oidAuction") Long oidAuction);

    @Modifying
    @Query("delete from Bid where auction.id = :auctionId")
    int deleteByAuctionId(@Param("auctionId")Long auctionid);
}

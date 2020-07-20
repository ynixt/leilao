package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import com.unkapps.leilao.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class AuctionDeleteService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private BidRepository bidRepository;

    @Transactional
    public void deleteById(@NotNull Long id) {
        Auction auction = auctionService.getAuctionForModify(id);

        bidRepository.deleteByAuctionId(id);

        auctionRepository.delete(auction);
    }
}

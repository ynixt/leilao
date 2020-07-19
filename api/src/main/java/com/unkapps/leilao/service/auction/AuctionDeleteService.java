package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AuctionDeleteService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionService auctionService;

    public void deleteById(@NotNull Long id) {
        Auction auction = auctionService.getAuctionForModify(id);

        auctionRepository.delete(auction);
    }
}

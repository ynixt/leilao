package com.unkapps.leilao.service.bid;

import com.unkapps.leilao.api.v1.dto.bid.BidRegisterDto;
import com.unkapps.leilao.domain.Bid;
import com.unkapps.leilao.repository.BidRepository;
import com.unkapps.leilao.service.auction.AuctionService;
import com.unkapps.leilao.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class BidRegisterService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuctionService auctionService;

    @PersistenceContext
    private EntityManager entityManager;

    public Bid register(BidRegisterDto dto) {
        Bid bid = toDomain(dto);

        return bidRepository.save(bid);
    }

    private Bid toDomain(BidRegisterDto dto) {
        Bid bid = new Bid();

        bid.setAuction(auctionService.getAuctionOrThrow(dto.getAuctionId()));
        bid.setMadeByUser(authService.getCurrentUser());
        bid.setValue(dto.getValue());
        bid.setDate(dto.getDate());

        return bid;
    }
}

package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionRegisterDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import com.unkapps.leilao.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class AuctionRegisterService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuthService authService;

    public Auction register(AuctionRegisterDto dto) {
        Auction auction = new Auction();
        auction.setName(dto.getName());
        auction.setUsed(dto.isUsed());
        auction.setInitialValue(dto.getInitialValue());
        auction.setUserResponsible(authService.getCurrentUser());
        auction.setOpenDate(dto.getOpenDate().withZoneSameInstant(ZoneId.of("UTC")));

        return auctionRepository.save(auction);
    }
}

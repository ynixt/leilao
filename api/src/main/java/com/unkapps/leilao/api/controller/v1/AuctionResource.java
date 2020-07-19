package com.unkapps.leilao.api.controller.v1;

import com.unkapps.leilao.api.dto.auction.AuctionCreatedDto;
import com.unkapps.leilao.api.dto.auction.AuctionRegisterDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.service.auction.AuctionRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auction")
public class AuctionResource {
    @Autowired
    private AuctionRegisterService auctionRegisterService;

    @PostMapping()
    public ResponseEntity<AuctionCreatedDto> register(@Valid @RequestBody AuctionRegisterDto dto) {
        Auction auction = auctionRegisterService.register(dto);
        AuctionCreatedDto createdDto = new AuctionCreatedDto(auction.getId());
        return ResponseEntity.ok(createdDto);
    }
}

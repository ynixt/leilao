package com.unkapps.leilao.api.v1.controller;

import com.unkapps.leilao.api.v1.dto.auction.AuctionCreatedDto;
import com.unkapps.leilao.api.v1.dto.auction.AuctionListDto;
import com.unkapps.leilao.api.v1.dto.auction.AuctionRegisterDto;
import com.unkapps.leilao.api.v1.dto.auction.AuctionSingleDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.service.auction.AuctionListService;
import com.unkapps.leilao.service.auction.AuctionRegisterService;
import com.unkapps.leilao.service.auction.AuctionSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auction")
public class AuctionResource {
    @Autowired
    private AuctionRegisterService auctionRegisterService;

    @Autowired
    private AuctionSingleService auctionSingleService;

    @Autowired
    private AuctionListService auctionListService;

    @GetMapping("/{id}")
    public ResponseEntity<AuctionSingleDto> getById(@PathVariable Long id) {
        Optional<AuctionSingleDto> result = auctionSingleService.getById(id);

        return ResponseEntity.of(result);
    }

    @GetMapping("")
    public ResponseEntity<Page<AuctionListDto>> list(Pageable pageable) {
        Page<AuctionListDto> results = auctionListService.list(pageable);

        return ResponseEntity.ok(results);
    }

    @PostMapping()
    public ResponseEntity<AuctionCreatedDto> register(@Valid @RequestBody AuctionRegisterDto dto) {
        Auction auction = auctionRegisterService.register(dto);
        AuctionCreatedDto createdDto = new AuctionCreatedDto(auction.getId());
        return ResponseEntity.ok(createdDto);
    }
}

package com.unkapps.leilao.api.v1.controller;

import com.unkapps.leilao.api.v1.dto.auction.AuctionListDto;
import com.unkapps.leilao.api.v1.dto.auction.AuctionRegisterDto;
import com.unkapps.leilao.api.v1.dto.auction.AuctionSingleDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.service.auction.AuctionDeleteService;
import com.unkapps.leilao.service.auction.AuctionListService;
import com.unkapps.leilao.service.auction.AuctionRegisterService;
import com.unkapps.leilao.service.auction.AuctionSingleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @Autowired
    private AuctionDeleteService auctionDeleteService;

    @ApiOperation(value = "Returns the corresponding id auction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the corresponding id auction"),
            @ApiResponse(responseCode = "404", description = "Auction not found"),
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AuctionSingleDto> getById(
            @ApiParam(required = true) @PathVariable Long id
    ) {
        Optional<AuctionSingleDto> result = auctionSingleService.getById(id);

        return ResponseEntity.of(result);
    }

    @ApiOperation(value = "List all auctions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all auctions"),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<AuctionListDto>> list(Pageable pageable) {
        Page<AuctionListDto> results = auctionListService.list(pageable);

        return ResponseEntity.ok(results);
    }

    @ApiOperation(value = "Creates a new auction", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns the auction that was created"),
            @ApiResponse(responseCode = "403", description = "User not authenticated"),
            @ApiResponse(responseCode = "400", description = "Dto doesn't respect the business role"),
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<Void> register(@Valid @RequestBody AuctionRegisterDto dto) {
        Auction auction = auctionRegisterService.register(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(auction.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Delete an auction", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Auction deleted"),
            @ApiResponse(responseCode = "401", description = "User is not the responsible of the auction"),
            @ApiResponse(responseCode = "403", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "Auction not found"),
    })
    public ResponseEntity<Void> deleteById(
            @ApiParam(required = true) @PathVariable Long id
    ) {
        auctionDeleteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

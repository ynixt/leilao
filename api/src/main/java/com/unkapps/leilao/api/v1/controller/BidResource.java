package com.unkapps.leilao.api.v1.controller;

import com.unkapps.leilao.api.v1.dto.bid.BidListDto;
import com.unkapps.leilao.api.v1.dto.bid.BidRegisterDto;
import com.unkapps.leilao.domain.Bid;
import com.unkapps.leilao.service.bid.BidListService;
import com.unkapps.leilao.service.bid.BidRegisterService;
import io.swagger.annotations.ApiOperation;
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
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/bid")
public class BidResource {
    @Autowired
    private BidRegisterService bidRegisterService;

    @Autowired
    private BidListService bidListService;

    @ApiOperation(value = "List all bid of an auction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all bid of an auction"),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<BidListDto>> list(
            Pageable pageable,
            @Valid @NotNull @RequestParam Long auctionId
    ) {
        Page<BidListDto> results = bidListService.list(pageable, auctionId);

        return ResponseEntity.ok(results);
    }

    @ApiOperation(value = "Creates a new bid", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bid was created"),
            @ApiResponse(responseCode = "403", description = "User not authenticated"),
            @ApiResponse(responseCode = "400", description = "Dto doesn't respect the business role"),
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<Void> register(@Valid @NotNull @RequestBody BidRegisterDto dto) {
        Bid bid = bidRegisterService.register(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bid.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

package com.unkapps.leilao.api.v1.controller;

import com.unkapps.leilao.api.v1.dto.bid.BidRegisterDto;
import com.unkapps.leilao.domain.Bid;
import com.unkapps.leilao.service.bid.BidRegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/bid")
public class BidResource {
    @Autowired
    private BidRegisterService bidRegisterService;

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

package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionSingleDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AuctionSingleService {
    @Autowired
    private AuctionRepository auctionRepository;

    public Optional<AuctionSingleDto> getById(@NotNull Long id) {
        Optional<Auction> optionalAuction = auctionRepository.getByIdFetchResponsible(id);

        if (optionalAuction.isPresent()) {
            return Optional.of(toSingleDto(optionalAuction.get()));
        }

        return Optional.empty();
    }

    public AuctionSingleDto toSingleDto(Auction auction) {
        AuctionSingleDto singleDto = new AuctionSingleDto();

        singleDto.setId(auction.getId());
        singleDto.setName(auction.getName());
        singleDto.setUserResponsible(new AuctionSingleDto.UserDto(auction.getUserResponsible().getId(),
                auction.getUserResponsible().getLogin()));
        singleDto.setUsed(auction.isUsed());
        singleDto.setOpenDate(auction.getOpenDate());
        singleDto.setEndDate(auction.getEndDate());
        singleDto.setInitialValue(auction.getInitialValue());

        return singleDto;
    }
}

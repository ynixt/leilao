package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionListDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuctionListService {
    @Autowired
    private AuctionRepository auctionRepository;

    public Page<AuctionListDto> list(Pageable pageable) {
        Page<Auction> auctions = auctionRepository.list(pageable);

        return toListDto(auctions);
    }

    public Page<AuctionListDto> toListDto(Page<Auction> auctions) {
        return auctions.map(auction -> {
            AuctionListDto listDto = new AuctionListDto();

            listDto.setId(auction.getId());
            listDto.setName(auction.getName());
            listDto.setUserResponsible(new AuctionListDto.UserDto(auction.getUserResponsible().getId(),
                    auction.getUserResponsible().getLogin()));
            listDto.setUsed(auction.isUsed());
            listDto.setFinished(auction.isFinished());
            listDto.setInitialValue(auction.getInitialValue());

            return listDto;
        });
    }
}

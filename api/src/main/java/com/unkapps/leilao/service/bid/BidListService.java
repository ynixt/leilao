package com.unkapps.leilao.service.bid;

import com.unkapps.leilao.api.v1.dto.bid.BidListDto;
import com.unkapps.leilao.domain.Bid;
import com.unkapps.leilao.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class BidListService {
    @Autowired
    private BidRepository bidRepository;

    public Page<BidListDto> list(Pageable pageable, @NotNull Long auctionId) {
        Page<Bid> bids = bidRepository.list(pageable, auctionId);

        return toListDto(bids);
    }

    public Page<BidListDto> toListDto(Page<Bid> bids) {
        return bids.map(auction -> {
            BidListDto listDto = new BidListDto();

            listDto.setId(auction.getId());
            listDto.setMadeByUser(new BidListDto.UserDto(auction.getMadeByUser().getId(),
                    auction.getMadeByUser().getLogin()));
            listDto.setValue(auction.getValue());
            listDto.setDate(auction.getDate());

            return listDto;
        });
    }
}

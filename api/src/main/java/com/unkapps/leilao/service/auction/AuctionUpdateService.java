package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionUpdateDto;
import com.unkapps.leilao.api.v1.exception.AppException;
import com.unkapps.leilao.api.v1.exception.dto.AppError;
import com.unkapps.leilao.api.v1.exception.dto.Code;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class AuctionUpdateService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionService auctionService;

    public Auction updateById(Long id, AuctionUpdateDto dto) {
        Auction auction = auctionService.getAuctionForModify(id);

        auction.setName(dto.getName());
        auction.setUsed(dto.isUsed());
        auction.setInitialValue(dto.getInitialValue());
        auction.setOpenDate(dto.getOpenDate().withZoneSameInstant(ZoneId.of("UTC")));

        if (dto.getEndDate() != null) {
            if (dto.getEndDate().isBefore(dto.getOpenDate())) {
                throw new AppException(AppError.of(Code.END_DATE_BEFORE_START_DATE));
            }
            auction.setEndDate(dto.getEndDate().withZoneSameInstant(ZoneId.of("UTC")));
        } else {
            auction.setEndDate(null);
        }

        return auctionRepository.save(auction);
    }
}

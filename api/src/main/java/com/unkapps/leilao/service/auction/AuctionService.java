package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.exception.AppException;
import com.unkapps.leilao.api.v1.exception.dto.AppError;
import com.unkapps.leilao.api.v1.exception.dto.Code;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import com.unkapps.leilao.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuctionRepository auctionRepository;

    public Auction getAuctionForModify(Long id) {
        Long userId = authService.getCurrentUser().getId();
        Optional<Auction> auction = auctionRepository.getById(id);

        if (auction.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND);
        } else if (auction.get().getUserResponsibleId().equals(userId) == false) {
            throw new AppException(HttpStatus.UNAUTHORIZED);
        }

        return auction.get();
    }

    public Auction getAuctionOrThrow(Long id) {
        Optional<Auction> auction = auctionRepository.getById(id);

        if (auction.isEmpty()) {
            throw new AppException(AppError.of(Code.AUCTION_NOT_FOUND));
        }

        return auction.get();
    }
}

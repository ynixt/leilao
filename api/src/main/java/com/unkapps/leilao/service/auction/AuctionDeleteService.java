package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.exception.AppException;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.repository.AuctionRepository;
import com.unkapps.leilao.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AuctionDeleteService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuthService authService;

    public void deleteById(@NotNull Long id) {
        Long userId = authService.getCurrentUser().getId();
        Optional<Auction> auction = auctionRepository.getById(id);

        if (auction.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND);
        } else if (auction.get().getUserResponsibleId().equals(userId) == false) {
            throw new AppException(HttpStatus.FORBIDDEN);
        }

        auctionRepository.deleteById(id);
    }
}

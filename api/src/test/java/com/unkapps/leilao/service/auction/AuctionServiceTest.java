package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.exception.AppException;
import com.unkapps.leilao.api.v1.exception.dto.Code;
import com.unkapps.leilao.api.v1.exception.dto.CodeError;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.AuctionRepository;
import com.unkapps.leilao.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuctionServiceTest {
    @Spy
    @InjectMocks
    private AuctionService auctionService;

    @Mock
    private AuctionRepository auctionRepository;

    @Mock
    private AuthService authService;

    private User createUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    private Auction createAuction(Long userId) {
        Auction auction = new Auction();
        auction.setId(213L);
        auction.setUserResponsibleId(userId);

        return auction;
    }

    @Test
    public void getAuctionForModifyWhenNotFound() {
        Long id = 10L;

        when(authService.getCurrentUser()).thenReturn(createUser(50L));
        when(auctionRepository.getById(eq(id))).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            auctionService.getAuctionForModify(id);
        });

        assertNotNull(exception.getHttpStatus(), "HttpStatus must be not null");

        assertEquals(exception.getHttpStatus(), HttpStatus.NOT_FOUND, "Error must contains correct code");
    }

    @Test
    public void getAuctionForModifyWhenNotResponsible() {
        Long id = 10L;

        when(authService.getCurrentUser()).thenReturn(createUser(50L));
        when(auctionRepository.getById(eq(id))).thenReturn(Optional.of(createAuction(100L)));

        AppException exception = assertThrows(AppException.class, () -> {
            auctionService.getAuctionForModify(id);
        });

        assertNotNull(exception.getHttpStatus(), "HttpStatus must be not null");

        assertEquals(exception.getHttpStatus(), HttpStatus.UNAUTHORIZED, "Error must contains correct code");
    }

    @Test
    public void getAuctionForModifyWhenOk() {
        Long id = 10L;

        when(authService.getCurrentUser()).thenReturn(createUser(100L));
        when(auctionRepository.getById(eq(id))).thenReturn(Optional.of(createAuction(100L)));

        Auction auction = auctionService.getAuctionForModify(id);
        assertNotNull(auction, "Auction must be not null");
    }

    @Test
    public void getAuctionOrThrowWhenNotFound() {
        Long id = 10L;

        when(auctionRepository.getById(eq(id))).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            auctionService.getAuctionOrThrow(id);
        });

        assertNotNull(exception.getError(), "Error must be not null");
        assertNotNull(exception.getError().getErrors(), "Errors must be not null");
        assertTrue(exception.getError().getErrors().size() == 1, "Errors must contain just one item");

        Object error = exception.getError().getErrors().get(0);

        assertTrue(error instanceof CodeError, "Error must be a CodeError");
        assertTrue(((CodeError) error).getCode() == Code.AUCTION_NOT_FOUND, "Error must contains correct code");
    }

    @Test
    public void getAuctionOrThrowWhenOk() {
        Long id = 10L;

        when(auctionRepository.getById(eq(id))).thenReturn(Optional.of(createAuction(100L)));

        Auction auction = auctionService.getAuctionOrThrow(id);

        assertNotNull(auction, "Auction must be not null");
    }
}

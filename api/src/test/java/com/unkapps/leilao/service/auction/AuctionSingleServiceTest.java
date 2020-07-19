package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionSingleDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuctionSingleServiceTest {
    @Spy
    @InjectMocks
    private AuctionSingleService auctionSingleService;

    @Mock
    private AuctionRepository auctionRepository;

    private Auction createAuction() {
        User user = new User();
        user.setId(131L);
        user.setLogin("login");
        user.setPassword("password");
        user.setActive(true);

        Auction auction = new Auction();
        auction.setId(213L);
        auction.setName("test");
        auction.setOpenDate(ZonedDateTime.now());
        auction.setEndDate(ZonedDateTime.now().plusDays(1));
        auction.setInitialValue(348.95f);
        auction.setUsed(false);
        auction.setUserResponsible(user);


        return auction;
    }

    @Test
    public void toSingleDto() {
        Auction auction = createAuction();
        AuctionSingleDto dto = auctionSingleService.toSingleDto(auction);

        assertEquals(auction.getId(), dto.getId());
        assertEquals(auction.getName(), dto.getName());
        assertEquals(auction.getInitialValue(), dto.getInitialValue());
        assertEquals(auction.getOpenDate(), dto.getOpenDate());
        assertEquals(auction.getEndDate(), dto.getEndDate());
        assertEquals(auction.isUsed(), dto.isUsed());
        assertEquals(auction.getUserResponsible().getId(), dto.getUserResponsible().getId());
        assertEquals(auction.getUserResponsible().getLogin(), dto.getUserResponsible().getLogin());

    }

    @Test
    public void getByIdWhenFound() {
        when(auctionRepository.getByIdFetchResponsible(any(Long.class))).thenReturn(Optional.of(createAuction()));
        assertTrue(auctionSingleService.getById(10L).isPresent());
    }

    @Test
    public void getByIdWhenNotFound() {
        when(auctionRepository.getByIdFetchResponsible(any(Long.class))).thenReturn(Optional.empty());
        assertTrue(auctionSingleService.getById(10L).isEmpty());
    }
}

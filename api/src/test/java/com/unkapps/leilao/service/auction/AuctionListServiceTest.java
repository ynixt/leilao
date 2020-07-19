package com.unkapps.leilao.service.auction;

import com.unkapps.leilao.api.v1.dto.auction.AuctionListDto;
import com.unkapps.leilao.domain.Auction;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuctionListServiceTest {
    @Spy
    @InjectMocks
    private AuctionListService auctionListService;

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

    private Page<Auction> createAuctions(int qty) {
        return new PageImpl<>(IntStream.of(qty).mapToObj(i -> createAuction()).collect(Collectors.toList()));
    }

    @Test
    public void toListDto() {
        Page<Auction> auctionPage = createAuctions(1);
        Page<AuctionListDto> dtoPage = auctionListService.toListDto(auctionPage);

        dtoPage.forEach(dto -> {
            Optional<Auction> optionalAuction = auctionPage.stream().filter(auction -> auction.getId() == dto.getId())
                    .findFirst();
            assertTrue(optionalAuction.isPresent());
            Auction auction = optionalAuction.get();

            assertEquals(auction.getId(), dto.getId());
            assertEquals(auction.getName(), dto.getName());
            assertEquals(auction.getInitialValue(), dto.getInitialValue());
            assertEquals(auction.getOpenDate() != null, dto.isFinished());
            assertEquals(auction.isUsed(), dto.isUsed());
            assertEquals(auction.getUserResponsible().getId(), dto.getUserResponsible().getId());
            assertEquals(auction.getUserResponsible().getLogin(), dto.getUserResponsible().getLogin());
        });
    }
}

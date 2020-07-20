import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { PageableDto } from './dto/pageable.dto';
import { BidListDto, BidSaveDto } from './dto/bid';
import { PageDto } from './dto/page.dto';

@Injectable({
  providedIn: 'root'
})
export class BidService {

  constructor(
    private http: HttpClient,
  ) { }

  list(auctionId: number, pageable: PageableDto): Observable<PageDto<BidListDto>> {
    return this.http.get<PageDto<BidListDto>>('/api/v1/bid/', {
      params: {
        ...pageable,
        auctionId: `${auctionId}`,
      } as any,
    });
  }

  newBid(dto: BidSaveDto): Observable<any> {
    return this.http.post<PageDto<BidListDto>>('/api/v1/bid/', dto);
  }
}

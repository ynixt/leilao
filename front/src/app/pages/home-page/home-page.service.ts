import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { PageableDto } from '../../shared/dto/pageable.dto';
import { PageDto } from '../../shared/dto/page.dto';
import { AuctionListDto } from 'src/app/shared/dto/auction/auction-list.dto';

@Injectable({
  providedIn: 'root'
})
export class HomePageService {

  constructor(
    private http: HttpClient,
  ) { }

  getAuctions(pageable: PageableDto): Observable<PageDto<AuctionListDto>> {
    return this.http.get<PageDto<AuctionListDto>>('/api/v1/auction/', {
      params: pageable as any,
    });
  }
}

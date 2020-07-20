import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AuctionSaveDto, AuctionSingleDto, AuctionListDto } from './dto/auction';
import { Observable } from 'rxjs';
import { PageableDto } from './dto/pageable.dto';
import { PageDto } from './dto/page.dto';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {

  constructor(
    private http: HttpClient,
  ) { }

  create(dto: AuctionSaveDto): Observable<any> {
    return this.http.post<any>('/api/v1/auction/', dto);
  }

  update(id: number, dto: AuctionSaveDto): Observable<any> {
    return this.http.put<any>(`/api/v1/auction/${id}`, dto);
  }

  get(id: number): Observable<AuctionSingleDto> {
    return this.http.get<AuctionSingleDto>(`/api/v1/auction/${id}`);
  }

  remove(id: number): Observable<any> {
    return this.http.delete<any>(`/api/v1/auction/${id}`);
  }

  getAuctions(pageable: PageableDto): Observable<PageDto<AuctionListDto>> {
    return this.http.get<PageDto<AuctionListDto>>('/api/v1/auction/', {
      params: pageable as any,
    });
  }
}

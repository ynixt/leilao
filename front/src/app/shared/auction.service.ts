import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AuctionSaveDto, AuctionSingleDto } from './dto/auction';
import { Observable } from 'rxjs';

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
    return this.http.put<any>(`/api/v1/auction/${id}`, dto).pipe();
  }

  get(id: number): Observable<AuctionSingleDto> {
    return this.http.get<AuctionSingleDto>(`/api/v1/auction/${id}`);
  }
}

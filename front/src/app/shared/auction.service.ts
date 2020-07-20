import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AuctionSaveDto } from './dto/auction';
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
}

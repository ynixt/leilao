import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ClrDatagridStateInterface } from '@clr/angular';
import { ChangeDetectorRef } from '@angular/core';

import { HomePageService } from './home-page.service';
import { AuctionListDto } from 'src/app/shared/dto/auction/auction-list.dto';
import { GridService } from '../../shared/components/grid/grid.service';
import { AuthState } from 'src/app/reducers/auth/auth.state';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';

@Component({
  selector: 'lei-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, AfterViewInit {
  auth$: Observable<AuthState>;

  auctions: AuctionListDto[] = [];
  total = 0;
  loading = true;

  constructor(
    private homePageService: HomePageService,
    private cdRef: ChangeDetectorRef,
    private gridService: GridService,
    store: Store<{ auth: AuthState }>
  ) {
    this.auth$ = store.pipe(select('auth'));
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.cdRef.detectChanges();
  }

  async refresh(state: ClrDatagridStateInterface): Promise<void> {
    const result = await this.gridService.refresh<AuctionListDto>(state, this.homePageService.getAuctions.bind(this.homePageService));

    this.total = result.totalElements;
    this.auctions = result.content;
    this.loading = false;
    this.cdRef.detectChanges();
  }
}

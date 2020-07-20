import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { ClrDatagridStateInterface } from '@clr/angular';
import { ChangeDetectorRef } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuctionListDto } from 'src/app/shared/dto/auction/auction-list.dto';
import { GridService } from '../../shared/components/grid/grid.service';
import { AuthState } from 'src/app/reducers/auth/auth.state';
import * as AuctionModalActions from 'src/app/reducers/auction-modal/auction-modal.actions';
import { AuctionState } from '../../reducers/auction/auction.state';
import { AuctionService } from 'src/app/shared/auction.service';

@Component({
  selector: 'lei-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, AfterViewInit, OnDestroy {
  private auctionSubscription: Subscription;
  private oldGridState: ClrDatagridStateInterface;

  auth$: Observable<AuthState>;

  auctions: AuctionListDto[] = [];
  total = 0;
  loading = true;

  constructor(
    private auctionService: AuctionService,
    private cdRef: ChangeDetectorRef,
    private gridService: GridService,
    private store: Store<{ auth: AuthState, auction: AuctionState }>
  ) {
    this.auth$ = store.pipe(select('auth'));
    const auction$: Observable<AuctionState> = store.pipe(select('auction'));
    this.auctionSubscription = auction$.subscribe(state => {
      if (state.saved) {
        this.refresh(this.oldGridState);
      }
    });
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.cdRef.detectChanges();
  }

  ngOnDestroy(): void {
    this.auctionSubscription.unsubscribe();
  }

  async refresh(gridState: ClrDatagridStateInterface): Promise<void> {
    const result = await this.gridService.refresh<AuctionListDto>(gridState, this.auctionService.getAuctions.bind(this.auctionService));

    this.total = result.totalElements;
    this.auctions = result.content;
    this.loading = false;
    this.cdRef.detectChanges();
    this.oldGridState = gridState;
  }

  edit(auctionId: number): void {
    this.store.dispatch(AuctionModalActions.openAuctionModal({ id: auctionId }));
  }
}

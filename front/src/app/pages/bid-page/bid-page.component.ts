import { Component, OnInit, ChangeDetectorRef, AfterViewInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClrDatagridStateInterface, ClrDatagridSortOrder } from '@clr/angular';
import { Observable, Subscription } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { formatCurrency } from '@angular/common';

import { GridService } from 'src/app/shared/components/grid/grid.service';
import { BidListDto, BidSaveDto } from '../../shared/dto/bid';
import { BidService } from '../../shared/bid.service';
import { AuthState } from 'src/app/reducers/auth/auth.state';
import { BidState } from 'src/app/reducers/bid/bid.state';
import * as BidActions from 'src/app/reducers/bid/bid.actions';

@Component({
  selector: 'lei-bid-page',
  templateUrl: './bid-page.component.html',
  styleUrls: ['./bid-page.component.scss']
})
export class BidPageComponent implements OnInit, AfterViewInit, OnDestroy {
  private oldGridState: ClrDatagridStateInterface;
  private bidSubscription: Subscription;

  auth$: Observable<AuthState>;
  bid$: Observable<BidState>;

  auctionId: number;

  bids: BidListDto[] = [];
  total = 0;
  loading = true;

  descSort = ClrDatagridSortOrder.DESC;

  formGroup = new FormGroup({
    value: new FormControl('', [
      Validators.required,
      Validators.min(0),
    ]),
  });

  constructor(
    private route: ActivatedRoute,
    private gridService: GridService,
    private bidService: BidService,
    private cdRef: ChangeDetectorRef,
    private store: Store<{ auth: AuthState, bid: BidState }>
  ) {
    this.auth$ = store.pipe(select('auth'));
    this.bid$ = store.pipe(select('bid'));

    this.bidSubscription = this.bid$.subscribe(bidState => {
      if (bidState.saved) {
        this.refresh(this.oldGridState);
        this.formGroup.reset();
      }
    });
  }

  ngOnInit(): void {
    this.auctionId = Number(this.route.snapshot.paramMap.get('auctionId'));
  }

  ngAfterViewInit(): void {
    this.cdRef.detectChanges();
  }

  ngOnDestroy(): void {
    this.bidSubscription.unsubscribe();
  }

  async refresh(gridState: ClrDatagridStateInterface): Promise<void> {
    const result = await this.gridService.refresh<BidListDto>(gridState,
      this.bidService.list.bind(this.bidService, this.auctionId),
    );

    this.total = result.totalElements;
    this.bids = result.content;
    this.loading = false;
    this.cdRef.detectChanges();
    this.oldGridState = gridState;
  }

  onSubmit(event): void {
    if (event) {
      event.preventDefault();
    }
    if (this.formGroup.valid) {
      const dto: BidSaveDto = {
        value: this.formGroup.get('value').value,
        auctionId: this.auctionId,
      };

      this.store.dispatch(BidActions.saveBidAuction({
        dto,
      }));
    }
  }

  treatError(httpError: HttpErrorResponse): string {
    if (httpError.status === 403) {
      return 'Usuário não autenticado';
    } else if (httpError.status >= 400 && httpError.status <= 500) {
      if (httpError.status === 400 && httpError.error && httpError.error.errors.length === 1) {
        if (httpError.error.errors[0].code === 'AUCTION_FINISHED') {
          return 'Leilão já terminado.';
        }
        if (httpError.error.errors[0].code === 'BID_SMALL_VALUE' && httpError.error.errors[0].extra != null) {
          return `O menor valor possível para lance é de ${formatCurrency(Number(httpError.error.errors[0].extra), 'pt', 'R$')}.`;
        }
      }

      return 'Dados preenchidos incorretamente';
    }

    return 'Um erro aconteceu. Tente novamente';
  }
}

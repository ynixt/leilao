import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuthState } from 'src/app/reducers/auth/auth.state';
import * as AuctionModalActions from 'src/app/reducers/auction-modal/auction-modal.actions';
import { AuctionModalState } from 'src/app/reducers/auction-modal/auction-modal.state';

@Component({
  selector: 'lei-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  auth$: Observable<AuthState>;
  auctionModal$: Observable<AuctionModalState>;

  constructor(
    private store: Store<{ auth: AuthState, auctionModal: AuctionModalState }>,
  ) {
    this.auth$ = store.pipe(select('auth'));
    this.auctionModal$ = store.pipe(select('auctionModal'));
  }

  ngOnInit(): void {
  }

  newAuction(): void {
    this.store.dispatch(AuctionModalActions.openAuctionModal({}));
  }
}

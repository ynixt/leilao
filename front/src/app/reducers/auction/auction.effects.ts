import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, catchError, exhaustMap, } from 'rxjs/operators';
import { AuctionService } from '../../shared/auction.service';

import * as AuctionActions from './auction.actions';

@Injectable()
export class AuctionEffects {
  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuctionActions.createAuction),
      exhaustMap(auction =>
        this.auctionService.create(auction.dto).pipe(
          map(_ => AuctionActions.saveSuccess()),
          catchError(error => of(AuctionActions.saveError({ error }))),
        )
      )
    )
  );

  update$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuctionActions.updateAuction),
      exhaustMap(auction =>
        this.auctionService.update(auction.id, auction.dto).pipe(
          map(_ => AuctionActions.saveSuccess()),
          catchError(error => of(AuctionActions.saveError({ error }))),
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuctionActions.removeAuction),
      exhaustMap(auction =>
        this.auctionService.remove(auction.id).pipe(
          map(_ => AuctionActions.removeSuccess()),
          catchError(error => of(AuctionActions.removeError({ error }))),
        )
      )
    )
  );

  get$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuctionActions.getAuction),
      exhaustMap(auction =>
        this.auctionService.get(auction.id).pipe(
          map(dto => AuctionActions.getAuctionSuccess({ dto })),
          catchError(error => of(AuctionActions.getAuctionError({ error }))),
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private auctionService: AuctionService,
  ) { }
}

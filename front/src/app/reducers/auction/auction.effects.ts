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

  constructor(
    private actions$: Actions,
    private auctionService: AuctionService,
  ) { }
}

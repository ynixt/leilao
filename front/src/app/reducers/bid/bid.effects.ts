import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, catchError, exhaustMap, } from 'rxjs/operators';

import * as BidActions from './bid.actions';
import { BidService } from '../../shared/bid.service';

@Injectable()
export class BidEffects {
  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(BidActions.saveBidAuction),
      exhaustMap(action =>
        this.bidService.newBid(action.dto).pipe(
          map(_ => BidActions.saveBidSuccess()),
          catchError(error => of(BidActions.saveBidError({ error }))),
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private bidService: BidService,
  ) { }
}

import { createReducer, on, ActionReducer, Action } from '@ngrx/store';
import * as AuctionActions from './auction.actions';
import { AuctionState } from './auction.state';

const initialState: AuctionState = {
  loading: false,
};

export const auctionReducer: ActionReducer<AuctionState, Action> = createReducer(initialState,
  on(AuctionActions.createAuction, (_, action) => {
    return {
      loading: true,
    };
  }),
  on(AuctionActions.saveSuccess, (_, action) => {
    return {
      loading: false,
      saved: true,
    };
  }),
  on(AuctionActions.saveError, (_, action) => {
    return {
      loading: false,
      error: action.error,
    };
  }),
);

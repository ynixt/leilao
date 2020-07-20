import { createReducer, on, ActionReducer, Action } from '@ngrx/store';
import * as AuctionActions from './auction.actions';
import { AuctionState } from './auction.state';

const initialState: AuctionState = {
  saving: false,
};

export const auctionReducer: ActionReducer<AuctionState, Action> = createReducer(initialState,
  on(AuctionActions.createAuction, _ => {
    return {
      saving: true,
    };
  }),
  on(AuctionActions.updateAuction, _ => {
    return {
      saving: true,
    };
  }),
  on(AuctionActions.saveSuccess, _ => {
    return {
      saving: false,
      saved: true,
    };
  }),
  on(AuctionActions.saveError, (_, action) => {
    return {
      saving: false,
      error: action.error,
    };
  }),

  on(AuctionActions.getAuction, _ => {
    return {
      loading: true,
    };
  }),
  on(AuctionActions.getAuctionSuccess, (_, action) => {
    return {
      loading: false,
      auction: action.dto,
    };
  }),
  on(AuctionActions.getAuctionError, (_, action) => {
    return {
      loading: false,
      error: action.error,
    };
  }),
);

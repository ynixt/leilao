import { createReducer, on, ActionReducer, Action } from '@ngrx/store';
import * as BidActions from './bid.actions';
import { BidState } from './bid.state';

const initialState: BidState = {
  saving: false,
};

export const bidReducer: ActionReducer<BidState, Action> = createReducer(initialState,
  on(BidActions.saveBidAuction, _ => {
    return {
      saving: true,
    };
  }),
  on(BidActions.saveBidSuccess, _ => {
    return {
      saving: false,
      saved: true,
    };
  }),
  on(BidActions.saveBidError, (_, action) => {
    return {
      saving: false,
      error: action.error,
    };
  }),
);

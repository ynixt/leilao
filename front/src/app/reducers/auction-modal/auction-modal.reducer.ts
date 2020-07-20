import { createReducer, on, ActionReducer, Action } from '@ngrx/store';
import * as AuctionModalActions from './auction-modal.actions';
import { AuctionModalState } from './auction-modal.state';

const initialState: AuctionModalState = {
  isOpen: false,
};

export const auctionModalReducer: ActionReducer<AuctionModalState, Action> = createReducer(initialState,
  on(AuctionModalActions.openAuctionModal, (_, action) => {
    return {
      isOpen: true,
      id: action.id,
    };
  }),
  on(AuctionModalActions.closeAuctionModal, (_, action) => {
    return {
      isOpen: false,
    };
  }),
);

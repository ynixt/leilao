import { createAction, props } from '@ngrx/store';

export const openAuctionModal = createAction('[AuctionModal] Open', props<{ id?: number }>());
export const closeAuctionModal = createAction('[AuctionModal] Close');

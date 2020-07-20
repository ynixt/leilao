import { createAction, props } from '@ngrx/store';
import { HttpErrorResponse } from '@angular/common/http';

import { AuctionSaveDto } from '../../shared/dto/auction';

export const createAuction = createAction('[Auction] Create', props<{ dto: AuctionSaveDto }>());
export const updateAuction = createAction('[Auction] Update', props<{ id: number, dto: AuctionSaveDto }>());

export const saveSuccess = createAction('[Auction] Save Success');
export const saveError = createAction('[Auction] Save Error', props<{ error: HttpErrorResponse }>());

export const getAuction = createAction('[Auction] Get', props<{ id: number }>());
export const getAuctionSuccess = createAction('[Auction] Get Success', props<{ dto: AuctionSaveDto }>());
export const getAuctionError = createAction('[Auction] Get Error', props<{ error: HttpErrorResponse }>());

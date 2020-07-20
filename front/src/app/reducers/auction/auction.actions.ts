import { createAction, props } from '@ngrx/store';
import { HttpErrorResponse } from '@angular/common/http';

import { AuctionSaveDto } from '../../shared/dto/auction';

export const createAuction = createAction('[Auction] Create', props<{ dto: AuctionSaveDto }>());
export const updateAuction = createAction('[Auction] Update', props<{ dto: AuctionSaveDto }>());

export const saveSuccess = createAction('[Auction] Save Success');
export const saveError = createAction('[Auction] Save Error', props<{ error: HttpErrorResponse }>());

import { createAction, props } from '@ngrx/store';
import { HttpErrorResponse } from '@angular/common/http';

import { BidSaveDto } from '../../shared/dto/bid';

export const saveBidAuction = createAction('[Bid] Save', props<{ dto: BidSaveDto }>());
export const saveBidSuccess = createAction('[Bid] Save Success');
export const saveBidError = createAction('[Bid] Save Error', props<{ error: HttpErrorResponse }>());

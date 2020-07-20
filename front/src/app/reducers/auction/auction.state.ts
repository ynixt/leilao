import { HttpErrorResponse } from '@angular/common/http';

export interface AuctionState {
  loading: boolean;
  error?: HttpErrorResponse;
  saved?: boolean;
}

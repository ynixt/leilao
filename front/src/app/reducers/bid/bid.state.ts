import { HttpErrorResponse } from '@angular/common/http';

export interface BidState {
  saving?: boolean;
  error?: HttpErrorResponse;
  saved?: boolean;
}

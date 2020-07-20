import { HttpErrorResponse } from '@angular/common/http';

import { AuctionSingleDto } from '../../shared/dto/auction';

export interface AuctionState {
  saving?: boolean;
  error?: HttpErrorResponse;
  saved?: boolean;

  loading?: boolean;
  auction?: AuctionSingleDto;
}

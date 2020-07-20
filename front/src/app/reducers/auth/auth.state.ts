import { LoginOutDto } from '../../shared/dto/auth';
import { HttpErrorResponse } from '@angular/common/http';

export interface AuthState {
  authenticated: boolean;
  out?: LoginOutDto;
  err?: HttpErrorResponse;
  loading: boolean;
}

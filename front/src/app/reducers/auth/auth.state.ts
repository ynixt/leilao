import { LoginOutDto } from '../../shared/dto/auth';

export interface AuthState {
  authenticated: boolean;
  out?: LoginOutDto;
  err?: any;
  loading: boolean;
}

import { createAction, props } from '@ngrx/store';
import { LoginInDto, LoginOutDto } from '../../shared/dto/auth/';

export const login = createAction('[Auth] Login', props<{ credentials: LoginInDto }>());
export const logout = createAction('[Auth] Logout');
export const renewToken = createAction('[Auth] Renew');

export const loginSuccess = createAction('[Auth] LoginSuccess', props<{ out: LoginOutDto }>());
export const loginError = createAction('[Auth] LoginError', props<{ error: any }>());

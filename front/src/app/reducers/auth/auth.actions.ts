import { createAction, props } from '@ngrx/store';
import { LoginInDto, LoginOutDto } from '../../shared/dto/auth/';
import { HttpErrorResponse } from '@angular/common/http';

export const login = createAction('[Auth] Login', props<{ credentials: LoginInDto }>());
export const register = createAction('[Auth] Register', props<{ credentials: LoginInDto }>());
export const logout = createAction('[Auth] Logout');
export const renewToken = createAction('[Auth] Renew');

export const loginSuccess = createAction('[Auth] LoginSuccess', props<{ out: LoginOutDto }>());
export const loginError = createAction('[Auth] LoginError', props<{ error: HttpErrorResponse }>());

export const logoutSuccess = createAction('[Auth] LogoutSuccess');

export const registerSuccess = createAction('[Auth] RegisterSuccess', props<{ out: LoginOutDto }>());
export const registerError = createAction('[Auth] RegisterSError', props<{ error: HttpErrorResponse }>());

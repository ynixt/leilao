import { createReducer, on, ActionReducer, Action } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { AuthState } from './auth.state';

const initialState: AuthState = {
  authenticated: false,
  loading: true,
};

export const authReducer: ActionReducer<AuthState, Action> = createReducer(initialState,
  on(AuthActions.login, (state, action) => {
    return {
      authenticated: false,
      loading: true,
    };
  }),
  on(AuthActions.renewToken, (state, action) => {
    return {
      authenticated: false,
      loading: true,
    };
  }),
  on(AuthActions.logout, (state, action) => {
    return {
      authenticated: false,
      loading: false,
    };
  }),
  on(AuthActions.loginSuccess, (state, action) => {
    return {
      authenticated: true,
      out: action.out,
      loading: false,
    };
  }),
  on(AuthActions.loginError, (state, action) => {
    return {
      authenticated: false,
      err: action.error,
      loading: false,
    };
  }),
);

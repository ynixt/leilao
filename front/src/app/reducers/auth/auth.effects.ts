import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, catchError, exhaustMap, } from 'rxjs/operators';
import { AuthService } from '../../shared/auth.service';

import * as AuthActions from './auth.actions';

@Injectable()
export class AuthEffects {
  login$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.login),
      exhaustMap(action =>
        this.authService.login(action.credentials).pipe(
          map(out => AuthActions.loginSuccess({ out })),
          catchError(error => of(AuthActions.loginError({ error }))),
        )
      )
    )
  );

  renewToken$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.renewToken),
      exhaustMap(_ =>
        this.authService.renewToken().pipe(
          map(out => AuthActions.loginSuccess({ out })),
          catchError(_ => of(AuthActions.logout())),
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private authService: AuthService
  ) { }
}

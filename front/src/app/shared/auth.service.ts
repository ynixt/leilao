import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

import { LoginInDto, LoginOutDto } from './dto/auth';
import { tap, catchError } from 'rxjs/operators';
import { Store } from '@ngrx/store';

import { renewToken, logout } from '../reducers/auth/auth.actions';
import { AuthState } from '../reducers/auth/auth.state';

export const COOKIE_NAME = 'token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    store: Store<{ auth: AuthState }>
  ) {
    setTimeout(() => {
      if (this.cookieService.check(COOKIE_NAME)) {
        store.dispatch(renewToken());
      } else {
        store.dispatch(logout());
      }
    }, 0);
  }

  public getTokenFromCookie(): string {
    if (this.cookieService.check(COOKIE_NAME)) {
      return this.cookieService.get(COOKIE_NAME);
    }

    return null;
  }

  public login(credentials: LoginInDto): Observable<LoginOutDto> {
    return this.http.post<LoginOutDto>('/api/v1/auth/login/', credentials).pipe(
      tap(out => {
        this.cookieService.set(COOKIE_NAME, out.token);
      })
    );
  }

  public renewToken(): Observable<LoginOutDto> {
    return this.http.post<LoginOutDto>('/api/v1/auth/renewToken/', null)
      .pipe(
        tap(out => {
          this.cookieService.set(COOKIE_NAME, out.token);
        }),
        catchError(err => {
          this.cookieService.delete(COOKIE_NAME);
          throw err;
        }),
      );
  }

  public logout(): void {
    this.cookieService.delete(COOKIE_NAME);
  }

  public register(credentials: LoginInDto): Observable<LoginOutDto> {
    return this.http.post<LoginOutDto>('/api/v1/auth/register/', credentials).pipe(
      tap(out => {
        this.cookieService.set(COOKIE_NAME, out.token);
      })
    );
  }
}

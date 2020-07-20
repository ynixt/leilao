import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { AuthService } from '../shared/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService,
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getTokenFromCookie();

    const headers: any = {
      'Content-Type': 'application/json; charset=utf-8',
      Accept: 'application/json',
    };

    if (token != null) {
      const authorization = token ? `Bearer ${this.authService.getTokenFromCookie()}` : null;
      headers.Authorization = authorization;
    }

    req = req.clone({
      setHeaders: headers,
    });

    return next.handle(req);
  }
}

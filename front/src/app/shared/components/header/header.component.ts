import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuthState } from 'src/app/reducers/auth/auth.state';

import * as AuthActions from '../../../reducers/auth/auth.actions';


@Component({
  selector: 'lei-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  auth$: Observable<AuthState>;

  constructor(private store: Store<{ auth: AuthState }>) {
    this.auth$ = store.pipe(select('auth'));
  }

  ngOnInit(): void {
  }

  signIn(): void {
    const credentials = {
      login: 'teste',
      password: '123456',
    };

    this.store.dispatch(AuthActions.login({ credentials }));
  }

  signOut(): void {
    this.store.dispatch(AuthActions.logout());
  }

}

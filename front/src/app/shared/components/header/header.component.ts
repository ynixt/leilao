import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuthState } from 'src/app/reducers/auth/auth.state';

import * as AuthActions from '../../../reducers/auth/auth.actions';
import { LoginModalComponent } from '../login-modal/login-modal.component';
import { RegisterModalComponent } from '../register-modal/register-modal.component';

@Component({
  selector: 'lei-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  auth$: Observable<AuthState>;

  @ViewChild(LoginModalComponent) loginModal: LoginModalComponent;
  @ViewChild(RegisterModalComponent) registerModal: RegisterModalComponent;

  constructor(private store: Store<{ auth: AuthState }>) {
    this.auth$ = store.pipe(select('auth'));
  }

  ngOnInit(): void {
  }

  signIn(): void {
    this.loginModal.openModal();
  }

  signOut(): void {
    this.store.dispatch(AuthActions.logout());
  }

  register(): void {
    this.registerModal.openModal();
  }
}

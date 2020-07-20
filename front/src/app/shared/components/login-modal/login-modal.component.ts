import { Component, OnInit, NgZone, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { tap } from 'rxjs/operators';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { AuthState } from 'src/app/reducers/auth/auth.state';
import * as AuthActions from '../../../reducers/auth/auth.actions';
import { LoginInDto } from '../../dto/auth';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'lei-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.scss']
})
export class LoginModalComponent implements OnInit, OnDestroy {
  auth$: Observable<AuthState>;
  modalIsOpen = false;

  private authSubscription: Subscription;

  loginFormGroup = new FormGroup({
    login: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(15),
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(15),
    ]),
  });

  constructor(
    private store: Store<{ auth: AuthState }>,
    zone: NgZone
  ) {
    this.auth$ = store.pipe(select('auth'));

    this.authSubscription = this.auth$.subscribe(state => {
      if (state.authenticated) {
        zone.run(() => this.modalIsOpen = false);
      }
    });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.authSubscription.unsubscribe();
  }

  openModal(): void {
    this.modalIsOpen = true;
  }

  signIn(credentials: LoginInDto): void {
    this.store.dispatch(AuthActions.login({ credentials }));
  }

  onSubmit(event): void {
    if (event) {
      event.preventDefault();
    }
    if (this.loginFormGroup.valid) {
      this.signIn({
        login: this.loginFormGroup.get('login').value,
        password: this.loginFormGroup.get('password').value,
      });
    }
  }

  treatError(httpError: HttpErrorResponse): string {
    if (httpError.status === 403) {
      return 'Credenciais incorretas';
    } else if (httpError.status >= 400 && httpError.status <= 500) {
      return 'Dados preenchidos incorretamente';
    }

    return 'Um erro aconteceu. Tente novamente';
  }
}

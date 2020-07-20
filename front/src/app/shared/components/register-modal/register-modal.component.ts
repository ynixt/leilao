import { Component, OnInit, NgZone, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { AuthState } from 'src/app/reducers/auth/auth.state';
import * as AuthActions from '../../../reducers/auth/auth.actions';
import { LoginInDto } from '../../dto/auth';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'lei-register-modal',
  templateUrl: './register-modal.component.html',
  styleUrls: ['./register-modal.component.scss']
})
export class RegisterModalComponent implements OnInit, OnDestroy {
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

  register(credentials: LoginInDto): void {
    this.store.dispatch(AuthActions.register({ credentials }));
  }

  onSubmit(event): void {
    if (event) {
      event.preventDefault();
    }
    if (this.loginFormGroup.valid) {
      this.register({
        login: this.loginFormGroup.get('login').value,
        password: this.loginFormGroup.get('password').value,
      });
    }
  }

  treatError(httpError: HttpErrorResponse): string {
    if (httpError.status >= 400 && httpError.status <= 500) {
      if (httpError.status === 400 && httpError.error && httpError.error.errors.length === 1
        && httpError.error.errors[0].code === 'USER_ALREADY_EXISTS') {
        return 'Login não disponível';
      }

      return 'Dados preenchidos incorretamente';
    }

    return 'Um erro aconteceu. Tente novamente';
  }
}

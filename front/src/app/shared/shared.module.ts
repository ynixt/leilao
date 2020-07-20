import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClarityModule } from '@clr/angular';
import { CookieService } from 'ngx-cookie-service';

import { HeaderComponent } from './components/header/header.component';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterModalComponent } from './components/register-modal/register-modal.component';

@NgModule({
  declarations: [
    HeaderComponent,
    LoginModalComponent,
    RegisterModalComponent,
  ],
  imports: [
    CommonModule,
    ClarityModule,
    ReactiveFormsModule,
  ],
  exports: [
    HeaderComponent,
    ClarityModule,
    ReactiveFormsModule,
  ],
  providers: [
    CookieService,
  ]
})
export class SharedModule { }

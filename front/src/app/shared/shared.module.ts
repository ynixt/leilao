import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClarityModule } from '@clr/angular';
import { CookieService } from 'ngx-cookie-service';

import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    HeaderComponent,
  ],
  imports: [
    CommonModule,
    ClarityModule,
  ],
  exports: [
    HeaderComponent,
    ClarityModule,
  ],
  providers: [
    CookieService,
  ]
})
export class SharedModule { }

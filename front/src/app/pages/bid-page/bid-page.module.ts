import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BidPageRoutingModule } from './bid-page-routing.module';
import { BidPageComponent } from './bid-page.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [BidPageComponent],
  imports: [
    CommonModule,
    SharedModule,
    BidPageRoutingModule,
  ]
})
export class BidPageModule { }

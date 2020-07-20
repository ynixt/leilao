import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BidPageComponent } from './bid-page.component';

const routes: Routes = [
  { path: ':auctionId', pathMatch: 'full', component: BidPageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BidPageRoutingModule { }

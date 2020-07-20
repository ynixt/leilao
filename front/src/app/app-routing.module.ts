import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', loadChildren: () => import('./pages/home-page/home-page.module').then(m => m.HomePageModule) },
  { path: 'lance', loadChildren: () => import('./pages/bid-page/bid-page.module').then(m => m.BidPageModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

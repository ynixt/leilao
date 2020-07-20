import { NgModule } from '@angular/core';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { authReducer, AuthEffects, auctionModalReducer, auctionReducer, AuctionEffects } from './reducers';

@NgModule({
  imports: [
    StoreModule.forRoot({
      auth: authReducer,
      auctionModal: auctionModalReducer,
      auction: auctionReducer,
    }, {
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
      }
    }),
    EffectsModule.forRoot([
      AuthEffects,
      AuctionEffects,
    ]),
  ],
})
export class AppStoreModule {
}

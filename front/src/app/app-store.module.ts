import { NgModule } from '@angular/core';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { authReducer, AuthEffects } from './reducers';

@NgModule({
  imports: [
    StoreModule.forRoot({
      auth: authReducer,
    }, {
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
      }
    }),
    EffectsModule.forRoot([
      AuthEffects
    ]),
  ],
})
export class AppStoreModule {
}

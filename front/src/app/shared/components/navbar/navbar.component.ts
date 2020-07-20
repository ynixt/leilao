import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuthState } from 'src/app/reducers/auth/auth.state';

@Component({
  selector: 'lei-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  auth$: Observable<AuthState>;

  constructor(store: Store<{ auth: AuthState }>) {
    this.auth$ = store.pipe(select('auth'));
  }

  ngOnInit(): void {
  }

}

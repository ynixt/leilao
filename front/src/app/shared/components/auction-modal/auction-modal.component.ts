import { Component, OnInit, OnDestroy, NgZone } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Observable, Subscription } from 'rxjs';
import { Store, select } from '@ngrx/store';

import { AuthState } from 'src/app/reducers/auth/auth.state';
import { AuctionModalState } from 'src/app/reducers/auction-modal/auction-modal.state';
import { LoginOutDto } from '../../dto/auth';
import * as AuctionModalActions from 'src/app/reducers/auction-modal/auction-modal.actions';
import * as AuctionActions from '../../../reducers/auction/auction.actions';
import { AuctionSaveDto } from '../../dto/auction';
import { AuctionState } from 'src/app/reducers/auction/auction.state';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'lei-auction-modal',
  templateUrl: './auction-modal.component.html',
  styleUrls: ['./auction-modal.component.scss']
})
export class AuctionModalComponent implements OnInit, OnDestroy {
  private authSubscription: Subscription;
  private auctionModalSubscription: Subscription;
  private auctionSubscription: Subscription;

  private internalModalIsOpen: boolean;

  auth$: Observable<AuthState>;
  auctionModal$: Observable<AuctionModalState>;
  auction$: Observable<AuctionState>;

  id: number;

  editing: boolean;

  get modalIsOpen(): boolean {
    return this.internalModalIsOpen;
  }

  set modalIsOpen(modalIsOpen: boolean) {
    this.internalModalIsOpen = modalIsOpen;
    if (!modalIsOpen) {
      this.store.dispatch(AuctionModalActions.closeAuctionModal());
      this.reset();
    }
  }

  startDate: Date;
  endDate?: Date;

  user: LoginOutDto;

  moment = moment;

  formGroup = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(50),
    ]),
    initialValue: new FormControl('', [
      Validators.required,
      Validators.min(0),
    ]),
    used: new FormControl('', [
    ]),
    openDate: new FormControl('', [
      Validators.required,
    ]),
    endDate: new FormControl('', [
    ]),
  });

  constructor(
    private store: Store<{ auth: AuthState, auctionModal: AuctionModalState, auction: AuctionState }>,
    zone: NgZone
  ) {
    this.auth$ = store.pipe(select('auth'));
    this.auction$ = store.pipe(select('auction'));
    this.auctionModal$ = store.pipe(select('auctionModal'));

    this.authSubscription = this.auth$.subscribe(state => {

      zone.run(() => {
        if (!state.authenticated) {
          this.internalModalIsOpen = false;
        }
        this.user = state.out;

        this.user = state.out;
      });
    });

    this.auctionModalSubscription = this.auctionModal$.subscribe(state => {
      if (state.isOpen) {
        zone.run(() => {
          this.open(state.id);
        });
      }
    });

    this.auctionSubscription = this.auction$.subscribe(state => {
      if (state.saved) {
        zone.run(() => {
          this.internalModalIsOpen = false;
        });
      } else if (state.auction) {
        zone.run(() => {
          this.formGroup.setValue({
            name: state.auction.name,
            initialValue: state.auction.initialValue,
            used: state.auction.used,
            openDate: '',
            endDate: '',
          });

          setTimeout(() => {
            this.startDate = moment(state.auction.openDate).toDate();

            if (state.auction.endDate != null) {
              this.endDate = moment(state.auction.endDate).toDate();
            }
          }, 0);
        });
      }
    });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.authSubscription.unsubscribe();
    this.auctionModalSubscription.unsubscribe();
    this.auctionSubscription.unsubscribe();
  }

  onSubmit(event): void {
    if (event) {
      event.preventDefault();
    }
    if (this.formGroup.valid) {
      const dto: AuctionSaveDto = {
        name: this.formGroup.get('name').value,
        initialValue: Number(this.formGroup.get('initialValue').value),
        used: this.formGroup.get('used').value,
        openDate: moment(this.startDate).toISOString(),
      };

      if (this.editing) {
        if (this.endDate) {
          dto.endDate = moment(this.endDate).toISOString();
        }

        this.store.dispatch(AuctionActions.updateAuction({
          id: this.id,
          dto,
        }));
      } else {
        this.store.dispatch(AuctionActions.createAuction({
          dto,
        }));
      }
    }
  }

  open(id?: number): void {
    this.id = id;
    this.editing = this.id != null;
    this.internalModalIsOpen = true;

    if (this.id != null) {
      this.store.dispatch(AuctionActions.getAuction({
        id: this.id,
      }));
    }
  }

  treatError(httpError: HttpErrorResponse): string {
    if (httpError.status >= 400 && httpError.status <= 500) {
      return 'Dados preenchidos incorretamente';
    }

    return 'Um erro aconteceu. Tente novamente';
  }

  private reset(): void {
    this.formGroup.reset();
    this.startDate = undefined;
    this.endDate = undefined;
  }

}

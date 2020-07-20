import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionModalComponent } from './auction-modal.component';

describe('AuctionModalComponent', () => {
  let component: AuctionModalComponent;
  let fixture: ComponentFixture<AuctionModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuctionModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuctionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

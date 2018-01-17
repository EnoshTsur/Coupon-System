import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllPurchasedCouponsByPriceComponent } from './get-all-purchased-coupons-by-price.component';

describe('GetAllPurchasedCouponsByPriceComponent', () => {
  let component: GetAllPurchasedCouponsByPriceComponent;
  let fixture: ComponentFixture<GetAllPurchasedCouponsByPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllPurchasedCouponsByPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllPurchasedCouponsByPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

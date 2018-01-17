import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCouponByPriceComponent } from './get-coupon-by-price.component';

describe('GetCouponByPriceComponent', () => {
  let component: GetCouponByPriceComponent;
  let fixture: ComponentFixture<GetCouponByPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCouponByPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCouponByPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

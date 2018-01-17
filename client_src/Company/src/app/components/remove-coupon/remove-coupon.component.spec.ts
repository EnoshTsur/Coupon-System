import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCouponComponent } from './remove-coupon.component';

describe('RemoveCouponComponent', () => {
  let component: RemoveCouponComponent;
  let fixture: ComponentFixture<RemoveCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

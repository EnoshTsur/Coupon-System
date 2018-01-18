import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';

@Component({
  selector: 'app-get-coupon',
  templateUrl: './get-coupon.component.html',
  styleUrls: ['./get-coupon.component.css']
})
export class GetCouponComponent implements OnInit {

  private date: Date;

  public coupons: Coupon[];
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  // Get All Coupons
  getCoupons() {
    this.coupons = new Array;

    const self = this;
    this._rest.getAllCoupons().subscribe(
      (_coupons) => {
        if (self.coupons.length === 0) {
          for (let c of _coupons)
          {
            c = new Coupon('', self.date, self.date, 0, '', '', 0, '', c);
            c._startDate = new Date(c._startDate).toDateString();
            c._endDate = new Date(c._endDate).toDateString();
            self.coupons.push(c);
          }
        }
      }
    );
  }

  // Set the right Coupon
  setCoupon(couponForSend: Coupon) {
    this.coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '', couponForSend);
  }
}

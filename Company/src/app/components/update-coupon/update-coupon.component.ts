import { Coupon } from './../../common/Coupon';
import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css']
})
export class UpdateCouponComponent implements OnInit {

  public date: Date;
  public price: number;

  public coupons: Coupon[];
  public coupon: Coupon = new Coupon('', this.date, this.date, 0 , '', '', 0, '' );

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  // Show all coupons on a drop down
  getCouponsForUpdate() {

    this.coupons = new Array;
    const self = this;

    this._rest.getAllCoupons().subscribe(
      (_coupons) => {
        if (self.coupons.length === 0) {

          for (let c of _coupons)
          {
            c = new Coupon('', self.date, self.date, 0, '', '', 0, '', c);
            self.coupons.push(c);
          }
        }
      }
    );
  }

  // Choosing the right coupon
  setCoupon(couponForSend: Coupon) {
    this.coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '', couponForSend);
  }

  // Updating Coupon
  updateCoupon() {
    this.coupon._endDate = this.date;
    this.coupon._price = this.price;

    // Checking value
    if (this.price <= 0) {
      swal('Error!' , 'Price can not be 0 or less' , 'warning');
      this.price = null;
      return;
    }

    const self = this;
    this._rest.updateCoupon(this.coupon).subscribe(
      (response) => {
          swal('Coupon: ' + self.coupon._title , ' updated with the new values, End Date:' +
          self.coupon._endDate + ' & Price:' + self.coupon._price , 'success');
      },
      () => {
          swal('Coupon: ' + self.coupon._title , ' was not updated, might be a connection problem' , 'error');
      }
    );
    this.date = null;
    this.price = null;
  }
}

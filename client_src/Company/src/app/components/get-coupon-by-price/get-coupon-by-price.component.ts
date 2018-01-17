import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';
import swal from 'sweetalert';

@Component({
  selector: 'app-get-coupon-by-price',
  templateUrl: './get-coupon-by-price.component.html',
  styleUrls: ['./get-coupon-by-price.component.css']
})
export class GetCouponByPriceComponent implements OnInit {

  public price: number;
  private date: Date;

  public coupons: Coupon[];

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  // Get Coupons by Price
  getCoupons() {

    this.coupons = new Array;
    const self = this;
    this._rest.getCouponByPrice(this.price).subscribe(
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
      },
      () => {
          swal('Not Found!' , 'There are no Coupons lower then price: ' + self.price + '$' , 'warning');
      }
    );
    console.log(self.coupons);
  }
}

import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';
import swal from 'sweetalert';

@Component({
  selector: 'app-get-all-purchased-coupons-by-price',
  templateUrl: './get-all-purchased-coupons-by-price.component.html',
  styleUrls: ['./get-all-purchased-coupons-by-price.component.css']
})
export class GetAllPurchasedCouponsByPriceComponent implements OnInit {

  public coupons: Coupon[];
  public price: number;

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  getCouponsByPrice() {

    this.coupons = new Array;
    const self = this;
    this._rest.getAllPurchasedCouponsByPrice(this.price).subscribe(
      (_coupons) => {
        if (self.coupons.length === 0) {
          for (let c of _coupons)
          {
            const date: Date = new Date();
            c = new Coupon('', date, date, 0, '', '', 0, '', c);
            c._startDate = new Date(c._startDate).toDateString();
            c._endDate = new Date(c._endDate).toDateString();
            self.coupons.push(c);
          }
        }
      },
      () => {
        swal('Not Found!' , 'There are no Coupons lower then price: ' + self.price + '$', 'warning');
      }
    );
  }
}

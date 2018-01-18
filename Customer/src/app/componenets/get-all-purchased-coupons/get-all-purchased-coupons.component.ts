import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';

@Component({
  selector: 'app-get-all-purchased-coupons',
  templateUrl: './get-all-purchased-coupons.component.html',
  styleUrls: ['./get-all-purchased-coupons.component.css']
})
export class GetAllPurchasedCouponsComponent implements OnInit {

  public coupons: Coupon[];

  constructor(private _rest: RestService) {
    this.getAllCoupons();
   }

  ngOnInit() {
  }

  getAllCoupons() {
  this.coupons = new Array;

    const self = this;
    this._rest.getAllPurchasedCoupons().subscribe(
      (_coupons) => {
        if (self.coupons.length === 0) {
          for (let c of _coupons)
          {
            const date: Date = new Date;
            c = new Coupon('', date, date, 0, '', '', 0, '', c);
            c._startDate = new Date(c._startDate).toDateString();
            c._endDate = new Date(c._endDate).toDateString();
            self.coupons.push(c);
          }
        }
      }
    );
  }
}

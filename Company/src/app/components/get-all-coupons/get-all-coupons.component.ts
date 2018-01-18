import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';

@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {

  public coupons: Coupon[];

  constructor(private _rest: RestService) {
    this.getCoupons();
   }

  ngOnInit() {
  }

  // Get all coupons
  getCoupons() {
    this.coupons = new Array;

    const self = this;
    this._rest.getAllCoupons().subscribe(
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

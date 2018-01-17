import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';
import swal from 'sweetalert';

@Component({
  selector: 'app-get-coupon-by-type',
  templateUrl: './get-coupon-by-type.component.html',
  styleUrls: ['./get-coupon-by-type.component.css']
})
export class GetCouponByTypeComponent implements OnInit {

  private date: Date;

  public type: string;
  public types = ['CAMPING', 'ELECTRICITY', 'FOOD', 'HEALTH', 'RESTURANS', 'SPORTS', 'TRAVELLING'];

  public coupons: Coupon[] = new Array;
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  // Setting the right type
  setType(typeForSend: string) {
    this.type = typeForSend;
    this.getCoupons();
  }

  // Get the Coupon by type
  getCoupons() {

    this.coupons = new Array;
    const self = this;
    this._rest.getCouponByType(this.type).subscribe(
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
        swal('Not Found!' , 'There no Coupons with the type: ' + self.type , 'warning');
      }
    );
  }
}

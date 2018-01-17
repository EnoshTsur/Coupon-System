import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';
import { element } from 'protractor';
import swal from 'sweetalert';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {

  private date: Date;

  public coupons: Coupon[];
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');

  constructor(private _rest: RestService) {
    this.setAllCoupons();
  }

  ngOnInit() {

  }

  // Set Coupons to all Coupons from the data base
  setAllCoupons() {
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


  // Purchasing Coupon
  purchaseCoupon() {

    this.coupon._startDate = new Date(this.coupon._startDate);
    this.coupon._endDate = new Date(this.coupon._endDate);

    const self = this;
    this._rest.purchaseCoupon(this.coupon).subscribe(
      (response) => {
          swal('Coupon:' + self.coupon._title , ' was purchased successfully' , 'success');
      },
      () => {
          swal('Error!', 'Already got this Coupon..' , 'error');
      }
    );
  }

  resetCoupon() {
    this.coupon._id = null;
    this.coupon._title = '';
    this.coupon._startDate = null;
    this.coupon._endDate = null;
    this.coupon._amount = 0;
    this.coupon._type = '';
    this.coupon._message = '';
    this.coupon._price = 0;
    this.coupon._image = '';
  }

  clickedButton(_coupon: Coupon) {
    this.coupon = new Coupon('', this.date, this.date , 0, '', '', 0, '', _coupon);

    const self = this;
    swal({
      title: 'Purchase Coupon' + _coupon._title,
      text: 'StartDate: ' + _coupon._startDate + '.' +
      ' EndDate: ' + _coupon._endDate +  '.' +
      ' Amount: ' + _coupon._amount + '.' +
      ' Type: ' + _coupon._type + '.' +
      ' Message: ' + _coupon._message + '.',
      icon: 'success',
      dangerMode: false,
      buttons: ['Cancel' , 'Purchase:' + _coupon._price + '$']
    }).then((isConfirm) => {
      if (isConfirm) {
         self.purchaseCoupon();
      } else {
        swal('Cancelled', 'Coupon: ' + self.coupon._title + ' was not purchased' , 'error');
      }
    });
  }
}

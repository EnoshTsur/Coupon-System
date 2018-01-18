import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon';
import swal from 'sweetalert';

@Component({
  selector: 'app-remove-coupon',
  templateUrl: './remove-coupon.component.html',
  styleUrls: ['./remove-coupon.component.css']
})
export class RemoveCouponComponent implements OnInit {

  private date: Date;

  public coupons: Coupon[] = new Array;
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

   // Show all coupons on a drop down
   getCouponsForRemove() {

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

      removeCoupon() {
        const self = this;
        swal({
          title: 'Are you sure?',
          text: 'This Coupon will deleted after confirm',
          icon: 'warning',
          dangerMode: true,
          buttons: ['Cancel' , 'Remove']
        }).then((isConfirm) => {
          if (isConfirm) {
            this._rest.removeCoupon(self.coupon._id).subscribe(
              (response) => {
                  swal('Coupon: ' + self.coupon._title , ' removed successfully', 'success');
                  self.coupon._title = '';
              },
              () => {
                  swal('Coupon: ' + self.coupon._title , ' was not removed, might be a connection problem', 'error');
              });
          } else {
            swal('Cancelled', 'Coupon: ' + self.coupon._title + ' was not removed' , 'error');
          }
        });
      }
}

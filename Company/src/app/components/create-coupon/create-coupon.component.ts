import { Coupon } from './../../common/Coupon';
import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {

  public title: string;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public type: string;
  public amount: number;
  public message: string;
  public price: number;
  public image: string;

  public coupon: Coupon;

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  createCoupon() {

   this.coupon = new Coupon(this.title, this.startDate, this.endDate, this.amount,
                            this.type , this.message, this.price, this.image);

  // Checking values
    if (this.amount <= 0) {
      swal('Amount', 'can not be 0 or less, try again', 'warning' );
      this.amount = null;
      return;
    }

    if (this.price <= 0) {
      swal('Price', 'can not be 0 or less, try again' , 'warning');
      this.price = null;
      return;
    }

    const self = this;
    this._rest.createCoupon(this.coupon).subscribe(
     (response) => {
         swal(self.coupon._title , ' was created.' , 'success');
     },
     () => {
         swal(self.coupon._title , ' was not created, coupon maybe already exist.' , 'error');
     }
   );
  this.clearMembers();
  }


  // Clear all members
  clearMembers() {
    this.title = null;
    this.startDate = null;
    this.endDate = null;
    this.amount = null;
    this.type = null;
    this.message = null;
    this.price = null;
    this.image = null;
  }
}

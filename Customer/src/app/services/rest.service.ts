import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Coupon } from '../common/Coupon';

@Injectable()
export class RestService {

  constructor(private _http: Http) { }

  // POST Purchase Coupon
  public purchaseCoupon(coupon: Coupon) {

    const promise = this._http.post('./purchaseCoupon/' , coupon);
    return promise;
  }

  // GET all Coupons
  public getAllCoupons() {

    const promise = this._http.get('./getAllCoupons')
    .map(
      (couponsResponse) => {
        return couponsResponse.json();
      }
    );
    return promise;
  }

   // GET all purchased Coupons
   public getAllPurchasedCoupons() {

        const promise = this._http.get('./getAllPurchasedCoupons')
        .map(
          (couponsResponse) => {
            return couponsResponse.json();
          }
        );
        return promise;
      }

      // GET all purchased Coupons by type
      public getAllPurchasedCouponsByType(type: string) {

            const promise = this._http.get('./getAllPurchasedCoupons/type/' + type)
            .map(
              (couponsResponse) => {
                return couponsResponse.json();
              }
            );
            return promise;
          }

          // GET all purchased Coupons by price
          public getAllPurchasedCouponsByPrice(price: number) {

            const promise = this._http.get('./getAllPurchasedCoupons/price/' + price)
            .map(
              (couponsResponse) => {
                return couponsResponse.json();
              }
            );
            return promise;
          }
}

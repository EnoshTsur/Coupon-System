import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Coupon } from '../common/Coupon';

@Injectable()
export class RestService {

  constructor(private _http: Http) { }

  // POST create Coupon
  public createCoupon(coupon: Coupon) {

    const promise = this._http.post('./createCoupon/' , coupon);
    return promise;
  }

  // Put update Coupon
  public updateCoupon(coupon: Coupon) {

    const promise = this._http.put('./updateCoupon/' , coupon);
    return promise;
  }

  // DELETE remove Coupon
  public removeCoupon(id: number) {

    const promise = this._http.delete('./removeCoupon/' + id);
    return promise;
  }

  // GET Company
  public getCompany() {

    const promise = this._http.get('./getThisCompany/')
    .map(
      (companyResponse) => {
        return companyResponse.json();
      }
    );
    return promise;
  }


  // GET All Coupons
  public getAllCoupons() {

    const promise = this._http.get('./getAllCoupons/')
    .map(
      (couponResponse) => {
        return couponResponse.json();
      }
    );
    return promise;
  }

    // GET Coupon by id
    public getCouponById(id: number) {

      const promise = this._http.get('./getCoupon/' + id)
      .map(
        (couponResponse) => {
          return couponResponse.json();
        }
      );
      return promise;
    }

  // GET Coupon by type
  public getCouponByType(type: string) {

    const promise = this._http.get('./getCouponsByType/' + type)
    .map(
      (couponResponse) => {
        return couponResponse.json();
      }
    );
    return promise;
  }

      // GET Coupon by price
      public getCouponByPrice(price: number) {

        const promise = this._http.get('./getCouponsByPrice/' + price)
        .map(
          (couponResponse) => {
            return couponResponse.json();
          }
        );
        return promise;
      }

  // GET Coupon by date
  public getCouponByDate(date: Date) {

    const stringDate: string = date.toString();
    const lastDate: number = Date.parse(stringDate);
    const promise = this._http.get('./getCouponsByDate/' + lastDate)
    .map(
      (couponResponse) => {
        return couponResponse.json();
      }
    );
    return promise;
  }
}

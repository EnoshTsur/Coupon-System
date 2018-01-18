import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { PurchaseCouponComponent } from './componenets/purchase-coupon/purchase-coupon.component';
import { GetAllPurchasedCouponsComponent } from './componenets/get-all-purchased-coupons/get-all-purchased-coupons.component';
import { GetAllPurchasedCouponsByTypeComponent } from './componenets/get-all-purchased-coupons-by-type/get-all-purchased-coupons-by-type.component';
import { GetAllPurchasedCouponsByPriceComponent } from './componenets/get-all-purchased-coupons-by-price/get-all-purchased-coupons-by-price.component';
import { HeaderComponent } from './componenets/header/header.component';
import { RestService } from './services/rest.service';


@NgModule({
  declarations: [
    AppComponent,
    PurchaseCouponComponent,
    GetAllPurchasedCouponsComponent,
    GetAllPurchasedCouponsByTypeComponent,
    GetAllPurchasedCouponsByPriceComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
     {
       path: 'purchaseCoupon' ,
       component: PurchaseCouponComponent
     } ,
     {
       path: 'getAllPurchasedCoupons' ,
       component : GetAllPurchasedCouponsComponent
     },
     {
       path: 'getAllPurchasedCouponsByType' ,
       component: GetAllPurchasedCouponsByTypeComponent
     },
     {
       path: 'getAllPurchasedCouponsByPrice' ,
       component: GetAllPurchasedCouponsByPriceComponent
     }
   ])
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { CreateCouponComponent } from './components/create-coupon/create-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { RemoveCouponComponent } from './components/remove-coupon/remove-coupon.component';
import { GetCompanyComponent } from './components/get-company/get-company.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { GetCouponComponent } from './components/get-coupon/get-coupon.component';
import { GetCouponByTypeComponent } from './components/get-coupon-by-type/get-coupon-by-type.component';
import { GetCouponByPriceComponent } from './components/get-coupon-by-price/get-coupon-by-price.component';
import { GetCouponByDateComponent } from './components/get-coupon-by-date/get-coupon-by-date.component';
import { RestService } from './services/rest.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CreateCouponComponent,
    UpdateCouponComponent,
    RemoveCouponComponent,
    GetCompanyComponent,
    GetAllCouponsComponent,
    GetCouponComponent,
    GetCouponByTypeComponent,
    GetCouponByPriceComponent,
    GetCouponByDateComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
     {
       path: 'createCoupon' ,
       component: CreateCouponComponent
     } ,
     {
       path: 'updateCoupon' ,
       component : UpdateCouponComponent
     },
     {
       path: 'removeCoupon' ,
       component: RemoveCouponComponent
     },
     {
       path: 'getCompany' ,
       component: GetCompanyComponent
     },
     {
       path: 'getAllCoupons' ,
       component: GetAllCouponsComponent
     },
     {
       path: 'getCoupon' ,
       component: GetCouponComponent
     },
     {
       path: 'getCouponByType',
       component : GetCouponByTypeComponent
     },
     {
       path: 'getCouponByPrice',
       component : GetCouponByPriceComponent
     },
     {
       path : 'getCouponByDate' ,
       component: GetCouponByDateComponent
     }
   ])
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

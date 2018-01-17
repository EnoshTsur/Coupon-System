import { Coupon } from './Coupon';
export class Customer {

  constructor (private custName: string, private password: string, private customer?: Customer,
               private coupons?: Coupon[], private id?: number) {

                if (this.customer != null) {
                   this.id = customer.id;
                   this.custName = customer.custName;
                   this.password = customer.password;
                   this.coupons = customer.coupons;
                 }
               }

  // Getters & Setters


  get _id(): number
  {
    return this.id;
  }

  set _id(newId: number)
  {
    this.id = newId;
  }

  get _custName(): string
  {
    return this.custName;
  }

  set _custName(newName: string)
  {
    this.custName = newName;
  }

  get _password(): string
  {
    return this.password;
  }

  set _password(newPassword: string)
  {
    this.password = newPassword;
  }

  get _coupons(): Coupon[]
  {
    return this.coupons;
  }

  set _coupons(newCoupons: Coupon[])
  {
    this.coupons = newCoupons;
  }

}

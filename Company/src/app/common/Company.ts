import { Coupon } from './Coupon';
export class Company {

  constructor(private compName: string , private password: string ,
              private email: string , private company?: Company, private coupons?: Coupon[] , private id?: number) {

                if (this.company != null) {

                  this.id = company.id;
                  this.compName = company.compName;
                  this.password = company.password;
                  this.email = company.email;
                  this.coupons = company.coupons;
                }
              }

  // Getters And Setters

  get _id(): number
  {
    return this.id;
  }

  set _id(newId: number)
  {
    this.id = newId;
  }

  get _compName(): string
  {
    return this.compName;
  }

  set _compName(newName: string)
  {
    this.compName = newName;
  }

  get _password(): string
  {
    return this.password;
  }

  set _password(newPassword: string)
  {
    this.password = newPassword;
  }

  get _email(): string
  {
    return this.email;
  }

  set _email(newEmail: string)
  {
    this.email = newEmail;
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

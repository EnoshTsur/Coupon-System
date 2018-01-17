import { Coupon } from './Coupon';
/**
 * Class that declaire customers
 */
export class Customer {
  // CTR
  constructor(private custName: string ,
     private password: string , private customer?: Customer, private coupons ?: Coupon[] , private id?: number) {

      if (this.customer != null) {

        this.id = customer.id;
        this.custName = customer.custName;
        this.password = customer.password;
        this.coupons = customer.coupons;
      }
     }

  // Getters and Setters
  get _id(): number
  {
  return this.id;
  }

  set _id(newNumber: number)
  {
  this.id = newNumber;
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

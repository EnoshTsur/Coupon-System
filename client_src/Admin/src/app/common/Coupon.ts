
/**
 * Class that declaire companies
 */
export class Coupon {
  // CTR
  constructor(private title: string , private startDate: Date , private endDate: Date, private amount: number ,
    private type: string , private message: string , private price: number , private image: string , private id?: number ) {
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

get _title(): string
{
  return this.title;
}

set _title(newTitle: string)
{
  this.title = newTitle;
}

get _startDate(): Date
{
  return this.startDate;
}

set _startDate(newDate: Date)
{
  this.startDate = newDate;
}

get _endDate(): Date
{
  return this.endDate;
}

set _endDate(newDate: Date)
{
  this.endDate = newDate;
}

get _amount(): number
{
  return this.amount;
}

set _amount(newAmount: number)
{
  this.amount = newAmount;
}

get _type(): string
{
  return this.type;
}

set _type(newType: string)
{
  this.type = newType;
}

get _message(): string
{
  return this.message;
}

set _message(newMessage: string)
{
  this.message = newMessage;
}

get _price(): number
{
  return this.price;
}

set _price(newPrice: number)
{
  this.price = newPrice;
}

get _image(): string
{
  return this.image;
}

set _image(newImage: string)
{
  this.image = newImage;
}

}

import { Component, OnInit } from '@angular/core';
import { Customer } from '../../common/Customer';
import { RestService } from '../../services/rest.service';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit {

  public customers: Customer[];
  public customer: Customer = new Customer('', '');

  constructor(private _rest: RestService) {
    this.getCustomers();
   }

  ngOnInit() {
  }

    // Shows all customers on a drop down button
    getCustomers() {
      this.customers = new Array;

      const self = this;
      this._rest.getAllCustomers().subscribe(
        (_customers) => {
          if (self.customers.length === 0) {

            for (let c of _customers)
            {
              c = new Customer('', '', c);
              self.customers.push(c);
            }
          }
        }
      );
    }

 // Choosing the correct customer
setCustomer(customerForSend: Customer) {
  this.customer = customerForSend;
}

}

import { Company } from './../../common/Company';
import { Component, OnInit } from '@angular/core';
import { Customer } from '../../common/Customer';
import { RestService } from '../../services/rest.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  public customers: Customer[];
  public customer: Customer = new Customer('', '');

  public password: string;

  constructor(private _rest: RestService) {
    this.getCustomersForUpdate();
   }

  ngOnInit() {
  }

  // Shows all customers on a drop down button
getCustomersForUpdate() {
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

// Choosing the correct customer to update
setCustomer(customerForSend: Customer) {
  this.customer = new Customer('', '', customerForSend);
}

  // Updating the customer new value
  updateCustomer() {
   this.customer._password = this.password;

   const self = this;
   this._rest.updateCustomer(this.customer).subscribe(
     (response) => {
         swal('Customer:' + self.customer._custName , ' was updated successfully.' , 'success');
     },
     () => {
         swal('Customer: ' + self.customer._custName , ' was not updated, might be a connection problem.' , 'error');
     }
    );

    this.password = null;
  }

}

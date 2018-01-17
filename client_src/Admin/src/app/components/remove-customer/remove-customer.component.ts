import { Component, OnInit } from '@angular/core';
import { Customer } from '../../common/Customer';
import { RestService } from '../../services/rest.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-remove-customer',
  templateUrl: './remove-customer.component.html',
  styleUrls: ['./remove-customer.component.css']
})
export class RemoveCustomerComponent implements OnInit {

  public customers: Customer[];
  public customer: Customer = new Customer('', '');

  constructor(private _rest: RestService) {
    this.getCustomersForRemove();
  }

  ngOnInit() {
  }

    // Shows all customers on a drop down button
getCustomersForRemove() {
  this.customers = new Array;

  const self = this;
  this._rest.getAllCustomers().subscribe(
    function(_customers)
    {
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

// Choosing the correct customer to remove
setCustomer(customerForSend: Customer) {
  this.customer = new Customer('', '', customerForSend);
}

removeCustomer() {

  const self = this;
  swal({
    title: 'Are you sure?',
    text: 'This Customer will deleted after confirm',
    icon: 'warning',
    dangerMode: true,
    buttons: ['Cancel' , 'Remove']
  }).then((isConfirm) => {
    if (isConfirm) {
      console.log(this.customer);
      this._rest.removeCustomer(self.customer._id).subscribe(
        (response) => {
            swal('Customer: ' + self.customer._custName , ' removed successfully', 'success');
            self.customer._custName = '';
        },
        () => {
            swal('Customer: ' + self.customer._custName , ' was not removed, might be a connection problem', 'error');
        });
    } else {
      swal('Cancelled', 'Customer: ' + self.customer._custName + ' was not removed' , 'error');
    }
  });

}
}

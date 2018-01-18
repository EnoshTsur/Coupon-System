import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Customer } from '../../common/Customer';
import swal from 'sweetalert';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  public customer: Customer;

  public name: string;
  public password: string;

  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  createCustomerButton() {
    this.customer = new Customer(this.name, this.password);
    const self = this;

    this._rest.createCustomer(this.customer).subscribe(
      (response) => {
          swal('Customer:' + self.customer._custName , ' was created' , 'success');
      },
      () => {
          swal('Customer:' + self.customer._custName , ' was not created, maybe name already exist', 'error');
      }
    );

    this.name = null;
    this.password = null;
  }
}

import { Component, OnInit } from '@angular/core';
import { Customer } from '../../common/Customer';
import { RestService } from '../../services/rest.service';

@Component({
  selector: 'app-get-customers',
  templateUrl: './get-customers.component.html',
  styleUrls: ['./get-customers.component.css']
})
export class GetCustomersComponent implements OnInit {

  public customers: Customer[];

  constructor(private _rest: RestService) {
   this.getCustomers();
   }

  ngOnInit() {
  }

   // Shows all customers
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
}

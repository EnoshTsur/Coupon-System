import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Company } from '../../common/Company';

@Component({
  selector: 'app-get-companies',
  templateUrl: './get-companies.component.html',
  styleUrls: ['./get-companies.component.css']
})
export class GetCompaniesComponent implements OnInit {

  public companies: Company[];

  constructor(private _rest: RestService) {
    this.getCompanies();
   }

  ngOnInit() {
  }

 // Shows all companies
 getCompanies() {
  this.companies = new Array;

   const self = this;
   this._rest.getAllCompanies().subscribe(
     (_companies) => {
       if (self.companies.length === 0) {

         for (let c of _companies)
         {
           c = new Company('', '', '', c);
           self.companies.push(c);
         }
       }
     }
   );
 }
}

import { Component, OnInit } from '@angular/core';
import { Company } from '../../common/Company';
import { RestService } from '../../services/rest.service';

@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css']
})
export class GetCompanyComponent implements OnInit {

  public companies: Company[];
  public company: Company = new Company('', '', '');

  constructor(private _rest: RestService) {
    this.getCompanies();
   }

  ngOnInit() {
  }

  // Shows all companies on a drop down button
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

  // Choosing the correct company
setCompany(companyForSend: Company) {
  this.company = companyForSend;
}


}

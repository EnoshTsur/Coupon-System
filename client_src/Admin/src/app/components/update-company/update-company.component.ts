import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Company } from '../../common/Company';
import swal from 'sweetalert';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {

  public companies: Company[];
  public company: Company = new Company('', '', '');

  public password: string;
  public email: string;

  constructor(private _rest: RestService) {
    this.getCompaniesForUpdate();
   }

  ngOnInit() {
  }


// Shows all companies on a drop down button
getCompaniesForUpdate() {

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

// Choosing the correct company to update
  setCompany(companyForSend: Company) {
  this.company = new Company('', '', '', companyForSend);
  }

  // Updating the company new values
  updateCompany() {
   this.company._password = this.password;
   this.company._email = this.email;

   const self = this;
   this._rest.updateCompany(this.company).subscribe(
     (response) => {
         swal('Company: ' + self.company._compName , ' was updated successfully.' , 'success');
     },
     () => {
         swal('Company: ' + self.company._compName , ' was not updated, might be a connection problem.', 'error');
     }
    );

    this.email = null;
    this.password = null;
  }
}

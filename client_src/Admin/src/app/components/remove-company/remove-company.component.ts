import { Component, OnInit } from '@angular/core';
import { Company } from '../../common/Company';
import { RestService } from '../../services/rest.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-remove-company',
  templateUrl: './remove-company.component.html',
  styleUrls: ['./remove-company.component.css']
})
export class RemoveCompanyComponent implements OnInit {

  public companies: Company[];
  public company: Company = new Company('', '', '');

  constructor(private _rest: RestService) {
    this.getCompaniesForRemove();
   }

  ngOnInit() {
  }

  // Shows all company on a drop down button
getCompaniesForRemove() {
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

removeCompany() {
  const self = this;
  swal({
    title: 'Are you sure?',
    text: 'This Company will deleted after confirm',
    icon: 'warning',
    dangerMode: true,
    buttons: ['Cancel' , 'Remove']
  }).then((isConfirm) => {
    if (isConfirm) {
      console.log(this.company);
      this._rest.removeCompany(self.company._id).subscribe(
        (response) => {
            swal('Company: ' + self.company._compName , ' removed successfully', 'success');
            self.company._compName = '';
        },
        () => {
            swal('Company: ' + self.company._compName , ' was not removed, might be a connection problem', 'error');
        });
    } else {
      swal('Cancelled', 'Company: ' + self.company._compName + ' was not removed' , 'error');
    }
  });

}
}

import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Company } from '../../common/Company';
import swal from 'sweetalert';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  public company: Company;

  public name: string;
  public password: string;
  public email: string;



  constructor(private _rest: RestService) { }

  ngOnInit() {
  }

  createCompanyButton() {
    this.company = new Company(this.name, this.password, this.email);

    const self = this;
    this._rest.createCompany(this.company).subscribe(
      (response) => {
          swal('Company:' + self.company._compName , ' was created successfully' , 'success');
      },
      () => {
          swal('Company:' + self.company._compName , ' was not created, maybe name already exist.', 'error');
      }
    );

    this.name = null;
    this.password = null;
    this.email = null;
  }


}

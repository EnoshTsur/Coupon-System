import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Company } from '../../common/Company';

@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css']
})
export class GetCompanyComponent implements OnInit {

  public company: Company = new Company('', '', '');

  constructor(private _rest: RestService) {
  }

  ngOnInit() {
    this.getCompany();
  }

  getCompany() {

    const self = this;
    this._rest.getCompany().subscribe(
      (response) => {
        self.company = new Company('', '', '', response);
      }
    );
  }

}

import { Injectable } from '@angular/core';
import { Company } from '../common/Company';
import { Customer } from '../common/Customer';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class RestService {

  constructor(private _http: Http) { }

 /**
 *----Company Rest----
 * */

// GET company
public getCompany(id: number) {

  const promise = this._http.get('./getCompany/' + id)
  .map(
    function (companyResponse)
    {
      return companyResponse.json();
    }
  );
  return promise;
}

// GET all companies
public getAllCompanies() {

  return this._http.get('./getAllCompanies/')
  .map(
    function (companiesResponse)
    {
      return companiesResponse.json();
    }
  );
}

// POST create new company
public createCompany(company: Company) {

  const promise = this._http.post('./createCompany' , company);
  return promise;
}

// PUT update company
public updateCompany(company: Company) {

  const promise = this._http.put('./updateCompany' , company);
  return promise;
}

// DELETE company
public removeCompany(id: number) {

  const promise = this._http.delete('./removeCompany/' + id );
  return promise;
}

/**
 * ----Customer Rest----
 * */
// GET customer
public getCustomer(id: number) {

  const promise = this._http.get('./getCustomer/' + id)
  .map(
    function (customerResponse)
    {
      return customerResponse.json();
    }
  );
  return promise;
}

// GET all customers
public getAllCustomers() {

  const promise = this._http.get('./getAllCustomers/')
  .map(
    function (customersResponse)
    {
      return customersResponse.json();
    }
  );
  return promise;
}

// POST create new customer
public createCustomer(customer: Customer) {

  const promise = this._http.post('./createCustomer' , customer);
  return promise;
}

// PUT update customer
public updateCustomer(customer: Customer) {

  const promise = this._http.put('./updateCustomer' , customer);
  return promise;
}

// DELETE remove customer
public removeCustomer(id: number) {

  const promise = this._http.delete('./removeCustomer/' + id);
  return promise;
}

}



import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RestService } from '../app/services/rest.service';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { GetCompaniesComponent } from './components/get-companies/get-companies.component';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { RouterModule } from '@angular/router';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { RemoveCompanyComponent } from './components/remove-company/remove-company.component';
import { GetCompanyComponent } from './components/get-company/get-company.component';
import { GetCustomersComponent } from './components/get-customers/get-customers.component';
import { GetCustomerComponent } from './components/get-customer/get-customer.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { RemoveCustomerComponent } from './components/remove-customer/remove-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    GetCompaniesComponent,
    CreateCompanyComponent,
    UpdateCompanyComponent,
    RemoveCompanyComponent,
    GetCompanyComponent,
    GetCustomersComponent,
    GetCustomerComponent,
    CreateCustomerComponent,
    UpdateCustomerComponent,
    RemoveCustomerComponent
  ],
  imports: [
    BrowserModule,
     FormsModule ,
     HttpModule ,
     RouterModule.forRoot([
       //Company
      {
        path:'createCompany' ,
        component: CreateCompanyComponent
      } ,
      {
        path: 'updateCompany' ,
        component : UpdateCompanyComponent
      },
      {
        path:'removeCompany' ,
        component: RemoveCompanyComponent
      },
      {
        path:'getCompany' ,
        component: GetCompanyComponent
      },
      {
        path:'getCompanies' ,
        component: GetCompaniesComponent
      },
      //Customer
      {
        path:'createCustomer' ,
        component: CreateCustomerComponent
      },
      {
        path:'updateCustomer' ,
        component: UpdateCustomerComponent
      },
      {
        path:'removeCustomer' ,
        component: RemoveCustomerComponent
      },
      {
        path:'getCustomer' ,
        component: GetCustomerComponent
      },
      {
        path:'getCustomers' ,
        component: GetCustomersComponent
      }
    ])
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCompaniesComponent } from './get-companies.component';

describe('GetCompaniesComponent', () => {
  let component: GetCompaniesComponent;
  let fixture: ComponentFixture<GetCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCompaniesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

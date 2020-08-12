import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLoanComponent } from './add-loan.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoanService } from '../service/loan.service';
import { Observable, of } from 'rxjs';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { throwError as observableThrowError } from 'rxjs';
import { By } from '@angular/platform-browser';


describe('AddLoanComponent', () => {
  let component: AddLoanComponent;
  let fixture: ComponentFixture<AddLoanComponent>;


  class MockedLoanService extends LoanService{

    addLoan(inputCustomerDetail: CustomerDetails): Observable<CustomerDetails>{
      const customerDetails: CustomerDetails = {
        firstName : 'rahul',
        lastName : 'dravid',
        loanNumber: 'HL-1001',
        address1 : 'Addr1',
        address2: 'Addr2',
        amount: 1000,
        city: 'tnj',
        loanTerm: 15,
        loanType: 'home loan'
      };
      return of(customerDetails);
    }
  }

  class MockedLoanServiceWithError extends LoanService{

    addLoan(inputCustomerDetail: CustomerDetails): Observable<CustomerDetails>{

      const errorMsg = {message: 'Authorization header is invalid'};
      return observableThrowError({ status: 400, error: errorMsg });
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, FormsModule],
      declarations: [ AddLoanComponent ],
      providers: [
        [{provide: LoanService, useClass: MockedLoanService }],
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create Add Loan Component', () => {
    expect(component).toBeTruthy();
  });

  // it('should Add Loan Save sucessfully', () => {
  //   const loanDetail = component.loanDetails;
  //   loanDetail.firstName = 'rahul';
  //   loanDetail.lastName = 'dravid';
  //   loanDetail.loanNumber = 'HL-1001';
  //   const form = fixture.debugElement.query(By.css('#addForm'));
  //   component.onSubmit(form);
  //   fixture.detectChanges();
  //   expect(component.msg).toEqual('Loan created Sucessfully');
  // });
});

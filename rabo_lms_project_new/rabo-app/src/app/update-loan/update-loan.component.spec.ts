import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLoanComponent } from './update-loan.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoanService } from '../service/loan.service';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { Observable, of } from 'rxjs';

class MockedLoanService extends LoanService{

  updateLoan(inputCustomerDetail: CustomerDetails): Observable<CustomerDetails>{
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

  getLoanDetails(loanNumber: string): Observable<CustomerDetails>{
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



describe('UpdateLoanComponent', () => {
  let component: UpdateLoanComponent;
  let fixture: ComponentFixture<UpdateLoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule, HttpClientTestingModule, FormsModule
      ],
      declarations: [ UpdateLoanComponent ],
      providers: [
        [{provide: LoanService, useClass: MockedLoanService }],
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create Update Component', () => {
    expect(component).toBeTruthy();
  });

  it('should Update Loan Save sucessfully', () => {

    component.onSubmit();
    fixture.detectChanges();
    expect(component.msg).toEqual('Loan Updated Sucessfully');
  });

});

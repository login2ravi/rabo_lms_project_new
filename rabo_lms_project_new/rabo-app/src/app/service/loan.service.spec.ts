import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LoanService } from './loan.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CustomerDetails } from '../model/CustomerDetails.model';

describe('LoanService', () => {
  let service: LoanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule ],
      declarations: [  ],
      providers: []
    });
    service = TestBed.inject(LoanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should return loan details by loan number in loan service', () => {

    const loanService = TestBed.inject(LoanService);
    const httpMock = TestBed.inject(HttpTestingController);

    const mockCustomerDetails: CustomerDetails = {
      firstName : 'rahul',
      lastName : 'dravid',
      loanNumber : 'HL-1001',
      address1 : '',
      address2 : '',
      city : 'tnj',
      amount : 5000,
      loanTerm : 15,
      loanType : 'Home Loan'

    };

    const loanNumber = 'HL-1001';

    loanService.getLoanDetails( 'HL-1001').subscribe((res) => {
      expect(res).toEqual(mockCustomerDetails);
    });


    const req = httpMock.expectOne('https://rabogatewayapp.cfapps.io/lmsLoanService/secure/getloandetails/' + loanNumber);
    expect(req.request.method).toBe('GET');
    req.flush(mockCustomerDetails);
  });


  it('should add loan in loan service', () => {

    const loanService = TestBed.inject(LoanService);
    const httpMock = TestBed.inject(HttpTestingController);

    const mockCustomerDetails: CustomerDetails = {
      firstName : 'rahul',
      lastName : 'dravid',
      loanNumber : 'HL-1001',
      address1 : '',
      address2 : '',
      city : 'tnj',
      amount : 5000,
      loanTerm : 15,
      loanType : 'Home Loan'

    };

    loanService.addLoan( mockCustomerDetails).subscribe((res) => {
      expect(res).toEqual(mockCustomerDetails);
    });


    const req = httpMock.expectOne('https://rabogatewayapp.cfapps.io/lmsLoanService/secure/addloan');
    expect(req.request.method).toBe('POST');
    req.flush(mockCustomerDetails);
  });

  it('should updateloan in loan service', () => {

    const loanService = TestBed.inject(LoanService);
    const httpMock = TestBed.inject(HttpTestingController);

    const mockCustomerDetails: CustomerDetails = {
      firstName : 'rahul',
      lastName : 'dravid',
      loanNumber : 'HL-1001',
      address1 : '',
      address2 : '',
      city : 'tnj',
      amount : 5000,
      loanTerm : 15,
      loanType : 'Home Loan'

    };

    loanService.updateLoan( mockCustomerDetails).subscribe((res) => {
      expect(res).toEqual(mockCustomerDetails);
    });


    const req = httpMock.expectOne('https://rabogatewayapp.cfapps.io/lmsLoanService/secure/updateloan');
    expect(req.request.method).toBe('PUT');
    req.flush(mockCustomerDetails);
  });



});

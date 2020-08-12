import { TestBed } from '@angular/core/testing';

import { AuthenticateService } from './authenticate.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { User } from '../model/User.model';
import { CustomerDetails } from '../model/CustomerDetails.model';

describe('AuthenticateService', () => {
  let service: AuthenticateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule ],
      declarations: [  ],
      providers: []
    });
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticateService);


  });

  it('should be created Authenticateservice service', () => {
    expect(service).toBeTruthy();
  });


  it('should http post user authenticate sevice', () => {

    const authenticateService = TestBed.inject(AuthenticateService);
    const httpMock = TestBed.inject(HttpTestingController);

    const mockUser: User = {
      token : 'Tokkent123',
      userName : 'admin',
      userrole : 'admin',
      isAdminUser : false,
      isLoggedIn : false,
      password : 'admin123'
    };


    authenticateService.authenticate('admin', 'admin123').subscribe((res) => {
      expect(res).toEqual(mockUser);
    });


    const req = httpMock.expectOne(`https://rabogatewayapp.cfapps.io/login`);
    expect(req.request.method).toBe('POST');
    req.flush(mockUser);
  });

  it('should return Search authenticationsevice', () => {

    const authenticateService = TestBed.inject(AuthenticateService);
    const httpMock = TestBed.inject(HttpTestingController);

    const mockCustomerDetails: CustomerDetails[] = [{
      firstName : 'rahul',
      lastName : 'dravid',
      loanNumber : 'HL-1001',
      address1 : '',
      address2 : '',
      city : 'tnj',
      amount : 5000,
      loanTerm : 15,
      loanType : 'Home Loan'

    }];


    authenticateService.getSearchDetails('rahul', 'dravid', 'HL-1001').subscribe((res) => {
      expect(res).toEqual(mockCustomerDetails);
    });

    const parmValue = 'firstName=rahul&lastName=dravid&loanNumber=HL-1001';
    const expectedUrl = 'https://rabogatewayapp.cfapps.io/lmsSearchService/secure/search?' + parmValue;
    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockCustomerDetails);
  });

});

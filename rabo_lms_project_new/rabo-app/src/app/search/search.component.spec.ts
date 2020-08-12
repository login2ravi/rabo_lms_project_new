import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AuthenticateService } from '../service/authenticate.service';
import { Observable, of } from 'rxjs';
import { User } from '../model/User.model';
import { UsersService } from '../service/users.service';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { Router } from '@angular/router';

class MockUserService extends UsersService{
  getUserData(): Observable<User>{
    const tempUser: User = {
      token : 'Tokkent123',
      userName : 'admin',
      userrole : 'admin',
      isAdminUser : true,
      isLoggedIn : false,
      password : 'admin123'
    };

    return of(tempUser) ;
  }
}

class  MockedAuthenticationService extends AuthenticateService{

  getSearchDetails(firstName, lastName, loanNumber): Observable<CustomerDetails[]>{

    const customerDetails: CustomerDetails[] = [{
      firstName : 'rahul',
      lastName : 'dravid',
      loanNumber: 'HL-1001',
      address1 : 'Addr1',
      address2: 'Addr2',
      amount: 1000,
      city: 'tnj',
      loanTerm: 15,
      loanType: 'home loan'
    }];
    return of(customerDetails);

  }

}



describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, FormsModule],
      declarations: [ SearchComponent ],
      providers: [
        UsersService, [{provide: AuthenticateService, useClass: MockedAuthenticationService }],
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should onSubmit() is sucessful', () => {
    const router = TestBed.inject(Router);

    const routerSpy = spyOn(router, 'navigate');
    fixture.detectChanges();
    const ngForm = component.ngForm;
    const loanDetail = component.loanDetails;
    loanDetail.firstName = 'rahul';
    loanDetail.lastName = 'dravid';
    loanDetail.loanNumber = 'HL-1001';

    component.onSubmit();
    fixture.detectChanges();

    expect(component.resultList.length).toEqual(1);
  });



});

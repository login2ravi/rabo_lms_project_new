import { async, ComponentFixture, TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserLoginComponent } from './user-login.component';
import { FormsModule } from '@angular/forms';
import { User } from '../model/User.model';
import { UsersService } from '../service/users.service';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticateService } from '../service/authenticate.service';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

class  MockedAuthenticationService extends AuthenticateService{
  private mockUser: BehaviorSubject<User>;


  authenticate(userName, password): Observable<User> {

    const tempUser: User = {
          token : 'Tokkent123',
          userName : 'admin',
          userrole : 'admin',
          isAdminUser : false,
          isLoggedIn : false,
          password : 'admin123'
        };

    return of(tempUser) ;
  }

}

const returnUser: User = {
  token : 'Tokkent123',
  userName : 'admin',
  userrole : 'admin',
  isAdminUser : false,
  isLoggedIn : false,
  password : 'admin123'
};

describe('UserLoginComponent', () => {
  let component: UserLoginComponent;
  let fixture: ComponentFixture<UserLoginComponent>;
  let usersService: UsersService;
  let authenticateService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, FormsModule],
      declarations: [ UserLoginComponent ],
      providers: [
        UsersService, [{provide: AuthenticateService, useClass: MockedAuthenticationService }],
      ]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    usersService = new UsersService();
    fixture = TestBed.createComponent(UserLoginComponent);
    authenticateService =   TestBed.inject(AuthenticateService);
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
    component.onSubmit();
    fixture.detectChanges();

    expect(component.invalidUserMessage).toBeUndefined();
  });

});


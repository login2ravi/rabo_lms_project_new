import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { UsersService } from '../service/users.service';
import { Observable, of } from 'rxjs';
import { User } from '../model/User.model';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

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

    getCurrentScreen(): Observable<string>{
      return of('search');
    }
  }



  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ ],
      declarations: [ HeaderComponent ],
      providers: [
        [{provide: UsersService, useClass: MockUserService }],
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create Header Component', () => {
    expect(component).toBeTruthy();
  });

  it('should Header Current active Screen', () => {
    const result = component.getcurrentScreen('search');
    fixture.detectChanges();
    expect(result).toEqual('active');

  });

  it('should Header  non-active Screen', () => {
    const result = component.getcurrentScreen('addloan');
    fixture.detectChanges();
    expect(result).not.toEqual('active');

  });
});

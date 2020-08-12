import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Router} from '@angular/router';
import { UsersService } from '../service/users.service';
import { Subscription } from 'rxjs';
import { User } from '../model/User.model';
import { AuthenticateService } from '../service/authenticate.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
public userData$: Subscription;
public invalidUserMessage: string;
user: User = new User();
@ViewChild(NgForm) ngForm: NgForm;

constructor(private userservice: UsersService, private router: Router, private authenticateService: AuthenticateService) {
  sessionStorage.setItem('username', null);
  sessionStorage.setItem('token', null);
}

  ngOnInit(): void {
  }

  onSubmit(): void{

    this.authenticateService.authenticate(this.user.userName,this.user.password).subscribe(responseData => {
      const tokenStr = 'Bearer ' + responseData['token'];

      if ( responseData['token'] != null){
        sessionStorage.setItem('username', responseData['username']);
        sessionStorage.setItem('token', tokenStr);


        sessionStorage.setItem('userrole', responseData['userrole']);
        const userDtl = new User();
        userDtl.userName = sessionStorage.getItem('username');

        if ( sessionStorage.getItem('userrole') === 'admin'){
          userDtl.isAdminUser = true;
        }else{
          userDtl.isAdminUser = false;
        }
        userDtl.isLoggedIn = true;
        this.userservice.setUserData(userDtl);
        this.userservice.setCurrentScreen('search');
        this.router.navigate(['/search'], { queryParams: { isLoggedIn: userDtl.isLoggedIn, isAdmin: userDtl.isAdminUser } });

      }else{
        this.invalidUserMessage = 'Invalid Username/Password';
       }
    }, errorMessage => {

      this.invalidUserMessage = 'Application not currently avaliable. Please try again later';
    });
  }
}

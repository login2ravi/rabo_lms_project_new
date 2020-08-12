import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { UsersService } from '../service/users.service';
import { User } from '../model/User.model';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  userData:User;

  constructor(private userservice : UsersService,private router: Router) { }

  ngOnInit(): void {
    
    sessionStorage.clear();
    this.userservice.setUserData(new User());
    this.router.navigate(['']);
    
  }

}

import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../model/User.model';
import {AuthenticateService} from './authenticate.service';


@Injectable({
  providedIn: 'root'
})
export class UsersService {

currentUser: User;

private userData$: BehaviorSubject<User>;
private currentScreen$: BehaviorSubject<string>;

  constructor( ) {
    this.userData$ = new BehaviorSubject(null);
    this.currentScreen$ = new BehaviorSubject(null);

   }

   getUserData(): Observable<User>{
     return this.userData$.asObservable();
   }

   setUserData(userDetails: User): void{

     this.userData$.next(userDetails);
   }


   getCurrentScreen(): Observable<string>{
    return this.currentScreen$.asObservable();
   }

   setCurrentScreen(currentScreen: string): void{
    this.currentScreen$.next(currentScreen);
  }

}

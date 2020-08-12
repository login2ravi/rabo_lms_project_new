import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { VirtualTimeScheduler, Observable } from 'rxjs';
import { User } from '../model/User.model';

class LoginUser{
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {



  constructor( private httpClient: HttpClient) { }


  authenticate(username, password) : Observable<User> {
    const headers = { 
                     // 'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token' };

    //return this.httpClient.post<User>('http://localhost:7003/login',
    return this.httpClient.post<User>('https://rabogatewayapp.cfapps.io/login',
    {"userName": username, "password": password}, {headers});
  }


  getSearchDetails(firstName, lastName, loanNumber){
    const headers = {
      'Access-Control-Allow-Origin': 'http://localhost:4200/*'
     };


    const token = sessionStorage.getItem('token');
    const httpOptions = {
        headers: { 'Content-Type': 'application/json',
        //'Access-Control-Allow-Origin': 'http://localhost:4200/*',
         'Authorization' : token},
      params: {'firstName': firstName,'lastName' : lastName,'loanNumber': loanNumber}
    };



//return this.httpClient.get<CustomerDetails[]>('http://localhost:7003/lmsSearchService/secure/search', httpOptions);
return this.httpClient.get<CustomerDetails[]>('https://rabogatewayapp.cfapps.io/lmsSearchService/secure/search', httpOptions);

}

}

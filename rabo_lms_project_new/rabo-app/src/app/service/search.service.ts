import { Injectable } from '@angular/core';
import {CustomerDetails} from '../model/CustomerDetails.model';
import { ngModuleJitUrl } from '@angular/compiler';
import {SearchResult} from '../model/Search.model';

import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient , HttpHeaders} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private searchResultArray: BehaviorSubject<Array<CustomerDetails>>;



  constructor(private httpClient: HttpClient){
    this.searchResultArray = new BehaviorSubject(null);
  }

  getSearchResult(): Observable<CustomerDetails[]>{
    return this.searchResultArray.asObservable();
  }

  setSearchResult(searchResultList: Array<CustomerDetails>): void{
        this.searchResultArray.next(searchResultList);
  }

}

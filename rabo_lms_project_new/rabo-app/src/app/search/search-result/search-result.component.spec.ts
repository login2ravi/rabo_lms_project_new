import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResultComponent } from './search-result.component';
import { SearchService } from 'src/app/service/search.service';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CustomerDetails } from 'src/app/model/CustomerDetails.model';
import { of, Observable } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';


class MockSearchService extends SearchService{

  getSearchResult(): Observable<CustomerDetails[]>{
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


describe('SearchResultComponent', () => {
  let component: SearchResultComponent;
  let fixture: ComponentFixture<SearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule, HttpClientTestingModule],
      declarations: [ SearchResultComponent ],
      providers: [
        [RouterTestingModule, HttpClientTestingModule, {provide: SearchService, useClass: MockSearchService }],
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create search-Result Component', () => {
    expect(component).toBeTruthy();
  });
});

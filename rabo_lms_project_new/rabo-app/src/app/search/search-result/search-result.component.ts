import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchService } from 'src/app/service/search.service';
import { CustomerDetails } from 'src/app/model/CustomerDetails.model';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  isLoggedIn: boolean;
  isAdminUser: boolean;
  resultList: Array<CustomerDetails>;

  constructor(private router: Router, private route: ActivatedRoute,
              private searchService: SearchService) { }

  ngOnInit(): void {


    this.searchService.getSearchResult().subscribe(
      searchResultArray => {
       this.resultList = searchResultArray;
     });


    this.route.queryParams.subscribe(params => {
      this.isLoggedIn = params.isLoggedIn;
      this.isAdminUser = (params.isAdmin === 'admin') ? true : false;
    });


  }

  onEdit(loanNumber: number): void{
    this.router.navigate(['/update-loan'],
    { queryParams: { loanNumber, isLoggedIn: this.isLoggedIn, isAdmin: this.isAdminUser  } });
  }


}

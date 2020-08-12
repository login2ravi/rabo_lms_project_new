import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {LoanService} from '../service/loan.service';
import {CustomerDetails} from '../model/CustomerDetails.model';

import { UsersService } from '../service/users.service';
import { HttpErrorResponse } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-add-loan',
  templateUrl: './add-loan.component.html',
  styleUrls: ['./add-loan.component.css']
})
export class AddLoanComponent implements OnInit {
  customerDetailModel: CustomerDetails;
  isLoggedIn = false;
  isAdminUser = false;
  msg: string;
  loanDetails: CustomerDetails = new CustomerDetails();

  constructor(private userservice: UsersService,
              private loanService: LoanService,
              private route: ActivatedRoute)  { }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.isLoggedIn = params.isLoggedIn;
      this.isAdminUser = params.isAdmin;
    });

    this.userservice.setCurrentScreen('addloan');

  }

  onSubmit(form: NgForm): void{
    if ( form.valid){
      this.loanService.addLoan(this.loanDetails).subscribe(responseData => {

        this.msg = 'Loan created Sucessfully';
        form.resetForm();
      }, (errorMessage) => {
        this.msg = errorMessage.error.message;
        if ( errorMessage.status === 400){
          this.msg = errorMessage.error.message;
        }else if ( errorMessage.status === 500){
        this.msg = 'Application not currently avaliable. Please try again later';
        }

    });
    }


  }

  onClearForm(form: NgForm): void{
    form.reset();
}
}

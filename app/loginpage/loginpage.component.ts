import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {HttpClient,HttpResponse} from '@angular/common/http'
import {DataAccessService} from '../services/data-access.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
  inputEmail;

  constructor(
    private dataAccess:DataAccessService,
    private router: Router,
    private route: ActivatedRoute,) { };

  resultlist:any;
  messages:any;
  ngOnInit(): void {

  };
       onClickSubmit(data) {
        console.log(data)
        this.dataAccess.loginuser(data)
        .subscribe(( data: HttpResponse<any>)=> {  this.resultlist=data
          
          if(this.resultlist){
            localStorage.setItem('token', this.resultlist.token);
            this.router.navigate(['/dashboard']);
          }
           
          },
          error => this.messages="Invalid Credentials" // You may show error message on the template
        )
     
     
   }





  }

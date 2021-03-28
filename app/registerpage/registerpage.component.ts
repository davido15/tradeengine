import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import {HttpHeaders} from '@angular/common/http'
import {DataAccessService} from '../services/data-access.service';
import { EmailValidator } from '@angular/forms';

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent implements OnInit {

  constructor(

    private dataAccess:DataAccessService,
    private router: Router,
    private route: ActivatedRoute) { };


  ngOnInit(): void {
  }

  onClickSubmit(data) {


    var obj = {
      "userId": 1,
      "id": 1,
      "title": "testdev",
      "body": "testdev"
    };

    var userdata = {
      "userid":12345,
      "email":data.email,
      "password":data.password,
      "firstname":data.fistname,
      "lastname":data.lastname,
      "funds":100

  }
 

//let headers = HttpHeaders.apply()

 
    var datanew = JSON.stringify(userdata)
    console.log("gotfrmdataaaaaaaaaaaaaaa"+datanew)

    this.dataAccess.createuser(datanew)
    .subscribe(
      success => alert("Registered  Successful."),
      error => alert("Error From Server")
    );


}
 // uses instance of data access service to call createTraveller function



}

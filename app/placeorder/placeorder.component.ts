import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import {HttpHeaders} from '@angular/common/http'
import {DataAccessService} from '../services/data-access.service';
import { EmailValidator } from '@angular/forms';

@Component({
  selector: 'app-placeorder',
  templateUrl: './placeorder.component.html',
  styleUrls: ['./placeorder.component.css']
})
export class PlaceorderComponent implements OnInit {
usertoken:any;
messages:any;
constructor(

  private dataAccess:DataAccessService,
  private router: Router,
  private route: ActivatedRoute) { };

  ngOnInit(): void {
    //this.usertoken = 
    var str = localStorage.getItem("token");
    var res = str.split("@");
    console.log(res[0]);
    this.usertoken= res[0];
  }


  onClickSubmit(data) {
    console.log(data)

    var orderdata = {
      "userid":this.usertoken,
      "product":"TSLA",
      "price":data.price,
      "quantity":data.quantity,
      "side":data.side,
    

  }
 
    var datanew = JSON.stringify(orderdata)
    console.log("dataaaaaaaaaaaaaaa"+datanew)

    this.dataAccess.placeorder(datanew)
    .subscribe(
      success => alert("Order Requested"),
      error => alert("Error From Server")
    );


}

}

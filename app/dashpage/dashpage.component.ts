import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-dashpage',
  templateUrl: './dashpage.component.html',
  styleUrls: ['./dashpage.component.css']
})
export class DashpageComponent implements OnInit {
  usertoken:any;
  constructor() { }

  ngOnInit(): void {
this.usertoken = localStorage.getItem("token");
console.log(this.usertoken)
  }

}

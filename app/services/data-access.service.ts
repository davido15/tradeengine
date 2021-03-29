import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataAccessService {
 
  userdata:any;
  orderdata:any;
  constructor(private client: HttpClient) { }


  getdata(): Observable<any> {
      return this.client.get<any>('https://jsonplaceholder.typicode.com/posts/2')
    }
   createuser(userdata){

      return this.client.post('http://localhost:4200/api/createuser', userdata, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
    };

    loginuser(userdata){

      return this.client.post('http://localhost:4200/api/login', userdata, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
    }   

    placeorder(orderdata){

      return this.client.post('http://localhost:4200/api/order', orderdata, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
    }  


}




import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataAccessService {
  Travellers:any;
  userdata:any;
  constructor(private client: HttpClient) { }


  getdata(): Observable<any> {
      return this.client.get<any>('https://jsonplaceholder.typicode.com/posts/2')
    }
   createuser(userdata){

      return this.client.post('https://60d5fbad7f00.ngrok.io/api/createuser', userdata, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
    };

    loginuser(userdata){

      return this.client.post('https://60d5fbad7f00.ngrok.io/api/login', userdata, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
    }   


}




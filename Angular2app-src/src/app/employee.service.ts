import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IEmployee } from './employee';
import { Observable } from 'rxjs/Observable';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
	
	
	private _url:string = "/assets/data/employees.json";

  constructor(private http:HttpClient) { }
  
  getEmployees() :Observable<IEmployee[]>
  {
	  
	  return this.http.get<IEmployee[]>(this._url);
	  
	 /* return [
	  
	  {"id":1,"name":"Swarup","age":"29"},
	  {"id":2,"name":"Kamor","age":"31"},
	  {"id":3,"name":"Mini","age":"29=7"}
		];*/
  }
}

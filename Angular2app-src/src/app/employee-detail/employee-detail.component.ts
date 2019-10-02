import { Component, OnInit } from '@angular/core';

import { EmployeeService } from '../employee.service';


@Component({
  selector: 'app-employee-detail',
  //templateUrl: './employee-detail.component.html',
  template:`
  
  <h2>Employee Detail List</h2>
  <ul *ngFor="let employee of employees">
  
  <li >
  {{employee.id}}   {{employee.name}}   {{employee.age}}

  
  </li>
  
  
  </ul>
  
  `,
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {
	
		public employees =[];


  constructor(private _employeeService:EmployeeService) { }

  ngOnInit() {
	  
	  	// this.employees= this._employeeService.getEmployees();
		 this._employeeService.getEmployees().subscribe(data=>this.employees=data);


	  
  }

}

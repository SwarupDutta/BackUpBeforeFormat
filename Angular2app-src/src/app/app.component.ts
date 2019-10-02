import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //template: `
 // <div>In line templtate <app-test> </app-test></div>
  
 // `
 
  //styleUrls: ['./app.component.css']
  styles:[`
  
	  div
	  {
		  color:red
	  }
  
  `]
})
export class AppComponent {
  title = 'hello-world test';
  public name="Viswas";
  public messageFromChild;
  

}

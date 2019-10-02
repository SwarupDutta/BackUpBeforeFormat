import { Component, OnInit,Input,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-test',
  //templateUrl: './test.component.html',
  template:`
  
  <!--<button (click)="onPress($event)">CLICK ME</button>
  <br/>
  <br/>
  {{greetingMessage}}
  
  <input #myInput type="text">
    <button (click)="logMessage(myInput.value)">CLICK ME</button>

  
  <h2 class="text-success">
Welcome {{"Mr"+ name}}
  </h2>
  
  
  
   <h2 [class]="successClass">
Welcome {{"Mr"+ name}} to Code Evolution
  </h2>
  
   <h2 [class.text-danger]="hasError">
Welcome {{"Mr"+ name}} to Code Evolution
  </h2>
  
  
  
   <h2 [ngClass]="messageClasses">
			Code Evolution
  </h2>
  
  
  <h2 [style.color]="hasError ? 'red' : 'orange'"> STYLEC BINDING1</h2>
  <h2 [style.color]="highlightColor"> STYLEC BINDING2</h2>
  <h2 [ngStyle]="returnCustomstyles()"> STYLEC BINDING3</h2>
  
  
    <div *ngIf="twoWayDataBindRequired; then thenBlock;else elseBlock"></div>

  

  


  <ng-template #thenBlock>
 <h2 >TWO WAY DAT BANDING THEN BLOCK</h2>
  <input [(ngModel)]="twoWayVar" type="text">
  {{twoWayVar}}  
  </ng-template>

  
  <ng-template #elseBlock>
  <p>
				Two way data binding is hidden
  
  </p>
  </ng-template>


<div [ngSwitch]="switchcolor">	
	<div *ngSwitchCase="'red'">	You picked red color</div>
	<div *ngSwitchCase="'green'">	You picked green color</div>
	<div *ngSwitchCase="'blue'">	You picked blue color</div>
	<div *ngSwitchDefault>	pick again</div>
</div>

<div *ngFor="let color of colors;index as i;first as f;last as l;even as e;odd as o;">

<p>{{i +"->"}} {{color}} {{"is first->"+f}}  {{"isLast->"+l}}</p>
</div>-->


<p> {{"From Parent" + parentData}}</p>
<button (click)="fireEvent()">SEND EVENT </button>
		
		
		


  
  
  `,
  //styleUrls: ['./test.component.css']
  styles:[]
  /*styles:[`
  
  .text-success
  {
	  color:green;
  }
  
  .text-danger
  {
	  color:red;
  }
  
  .text-special
  {
	  font-style:italic;
  }
  `]*/
})
export class TestComponent implements OnInit {
	
	
	@Input() public parentData;
	
	@Output() public childEvent=new EventEmitter();

  
  testMessage:string;
  switchcolor="redtt";
  public successClass:string="text-success";
  public name:string= "Swarup Dutta";
  public hasError:boolean=true;
  public isSpecial:boolean=true;
  
  public twoWayDataBindRequired:boolean=true;

  public highlightColor="orange";
  public colors:string[]=["red","green","blue"];
  
  public greetingMessage:string;
  
  public twoWayVar:string="two way var";
  public messageClasses={
	  
	  "text-success":!this.hasError,
	  "text-danger":this.hasError,
	  "text-special":this.isSpecial

	  
	  
  };
  
  
  public titleStyles = {
	  
	  color:"blue",
	  fontStyle:"italic"
  }


  constructor() { }

  ngOnInit() {
	 this. testMessage="YEEES TEST WORKS";
  }
  
  
  logMessage(value)
  {
	  
	  	  console.log(value);

	  
  }
  
  fireEvent()
  {
	  this.childEvent.emit('Hey Viswas from code evolution');
  }
  
  
  onPress(event)
  {
	  
	  console.log(event);
	  this.greetingMessage="Wel come to Code evaluation"+event.type;
  }
  
  greeting()
  {
	  
	  return "Hello"+ this.name;
  }
  
  returnCustomstyles()
  {
	  
	  return this.titleStyles;
	  
  }

}

import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from'@angular/forms';
import { AppComponent } from './app.component';
import { Component1Component } from './component1/component1.component';
import { Component2Component } from './component2/component2.component';
import { Component3Component } from './component3/component3.component';
import { Component4Component } from './component4/component4.component';
import { FirstCapPipe } from './pipes/first-cap.pipe';
import { Component5Component } from './component5/component5.component';
import { PopulateService } from './populate.service';
import { Component6Component } from './component6/component6.component';
import { RouterModule } from '@angular/router';
import { routes } from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    Component1Component,
    Component2Component,
    Component3Component,
    Component4Component,
    FirstCapPipe,
    Component5Component,
    Component6Component
  ],
  imports: [
    
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
    
  ],
  providers: [PopulateService],
  bootstrap: [AppComponent]
})
export class AppModule { }

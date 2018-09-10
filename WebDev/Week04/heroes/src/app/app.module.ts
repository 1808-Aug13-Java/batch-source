import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TourComponent } from './components/tour/tour.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ListComponent } from './components/list/list.component';
import { DetailsComponent } from './components/details/details.component';
import { RouterModule } from '../../node_modules/@angular/router';

import { appRoutes } from './routes';
import { Globals } from './data/Globals';

@NgModule({
  declarations: [
    AppComponent,
    TourComponent,
    DashboardComponent,
    ListComponent,
    DetailsComponent
  ],
  imports: [
	BrowserModule,
	FormsModule,
	RouterModule.forRoot(appRoutes)
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }

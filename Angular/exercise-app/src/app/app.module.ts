import { UserService } from './services/user.service';
import { FormsModule } from '@angular/forms';
import { appRoutes } from './routes';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { NavComponent } from './components/nav/nav.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';
import { FirstCapPipe } from './pipes/first-cap.pipe';
import { ApiCallComponent } from './components/api-call/api-call.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HighlightComponent,
    NavComponent,
    ProfileComponent,
    SelectComponent,
    TableComponent,
    FirstCapPipe,
    ApiCallComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

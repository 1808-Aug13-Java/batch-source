import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HighlightComponentComponent } from './components/highlight-component/highlight-component.component';
import { ProfileComponentComponent } from './components/profile-component/profile-component.component';
import { SelectComponent } from './components/select/select.component';
import { FormsModule } from '@angular/forms';
import { TableComponent } from './components/table/table.component';
import { UserComponent } from './components/user/user.component'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { UserService } from './services/user.service';
import { appRoutes } from './routes';
import { NavComponent } from './components/nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    HighlightComponentComponent,
    ProfileComponentComponent,
    SelectComponent,
    TableComponent,
    UserComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService], // services
  bootstrap: [AppComponent]
})
export class AppModule { }

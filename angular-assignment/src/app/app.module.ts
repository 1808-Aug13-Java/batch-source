import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HighlighterComponentComponent } from './components/highlighter-component/highlighter-component.component';
import { ProfileComponentComponent } from './components/profile-component/profile-component.component';
import { SelectComponentComponent } from './components/select-component/select-component.component';
import { TableComponentComponent } from './components/table-component/table-component.component';
import { UserComponentComponent } from './components/user-component/user-component.component';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { UserService } from './services/user.service';
import { NavComponentComponent } from './components/nav-component/nav-component.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';

@NgModule({
  declarations: [
    AppComponent,
    HighlighterComponentComponent,
    ProfileComponentComponent,
    SelectComponentComponent,
    TableComponentComponent,
    UserComponentComponent,
    NavComponentComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

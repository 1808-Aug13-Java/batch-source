import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HighlightTextComponent } from './highlight-text/highlight-text.component';
import { ProfileComponent } from './profile/profile.component';
import { SelectButtonsComponent } from './select-buttons/select-buttons.component';
import { UserTableComponent } from './user-table/user-table.component';
import { UserHttpComponent } from './user-http/user-http.component';
import { NavComponent } from './nav/nav.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';

@NgModule({
  declarations: [
    AppComponent,
    HighlightTextComponent,
    ProfileComponent,
    SelectButtonsComponent,
    UserTableComponent,
    UserHttpComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

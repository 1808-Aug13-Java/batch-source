import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';
import { UserComponent } from './components/user/user.component';
import { NavComponent } from './components/nav/nav.component';
import { PropercasingPipe } from './propercasing.pipe';
import { PostService } from './post.service';
import { appRoutes } from './routes';

@NgModule({
  declarations: [
    AppComponent,
    HighlightComponent,
    ProfileComponent,
    SelectComponent,
    TableComponent,
    UserComponent,
    NavComponent,
    PropercasingPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }

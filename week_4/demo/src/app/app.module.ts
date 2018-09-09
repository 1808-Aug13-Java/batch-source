import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { FirstComponent } from './components/first/first.component';
import { DatabindComponent } from './components/databind/databind.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { CapitalizeFirstLetterPipe } from './pipes/capitalize-first-letter.pipe';
import { PostService } from './services/post.service';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { RouterModule } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    FirstComponent,
    DatabindComponent,
    FavoriteComponent,
    ClickerComponent,
    SDirectivesComponent,
    ADirectivesComponent,
    PipeDemoComponent,
    ConvertToSpacePipe,
    CapitalizeFirstLetterPipe,
    HttpExampleComponent,
    NavComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }

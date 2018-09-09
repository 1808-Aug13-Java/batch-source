import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import {FirstComponent} from './components/first/first.component';
import { DatabindComponent } from './components/databind/databind.component';
import {FormsModule} from '@angular/forms';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { ConvertToZPipe } from './pipes/convert-to-z.pipe';
import {PostService} from './services/post.service';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import {HttpClientModule} from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router'
import { appRoutes } from './routes';
import { NavComponent } from './components/nav/nav.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';

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
    ConvertToZPipe,
    HttpExampleComponent,
    NavComponent,
    DirectivesComponent,
    PostDetailsComponent
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

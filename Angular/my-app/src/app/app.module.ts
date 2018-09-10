import { appRoutes } from './routes';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { FirstComponent } from './components/first/first.component';
import { DatabindComponent } from './components/databind/databind.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { CapitalizePipe } from './pipes/capitalize.pipe';
import { FirstCapPipe } from './pipes/first-cap.pipe';
import { HttpClientModule } from '@angular/common/http';
import { PostService } from './services/post.service';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { RouterModule } from '@angular/router';
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
    CapitalizePipe,
    FirstCapPipe,
    HttpExampleComponent,
    NavComponent,
    DirectivesComponent,
    PostDetailsComponent,
  ],
  imports: [  // External modules
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PostService], //services
  bootstrap: [AppComponent] //designates root component
})
export class AppModule { }

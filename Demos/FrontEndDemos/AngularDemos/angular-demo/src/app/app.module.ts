import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { FirstComponent } from './components/first/first.component';
import { DatabindComponent } from './components/databind/databind.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { ClickerComponent } from './components/clicker/clicker.component';
import { CapitalizeFirstLetterPipe } from './pipes/capitalize-first-letter.pipe';
import { PostService } from './services/post.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { RouterModule } from '../../node_modules/@angular/router';
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
    SDirectivesComponent,
    ADirectivesComponent,
    PipeDemoComponent,
    ConvertToSpacePipe,
    ClickerComponent,
    CapitalizeFirstLetterPipe,
    HttpExampleComponent,
    NavComponent,
    DirectivesComponent,
    PostDetailsComponent
  ],
  imports: [ // external modules
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PostService], // services
  bootstrap: [AppComponent] // designates root component
})
export class AppModule { }

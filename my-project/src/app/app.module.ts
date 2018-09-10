import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms'

import { AppComponent } from './app.component';
import {FirstComponent} from './Components/first/first.component';
import { DatabindComponent } from './Components/databind/databind.component';
import { FavoriteComponent } from './Components/favorite/favorite.component';
import { ClickerComponent } from './Components/clicker/clicker.component';
import { SDirectivesComponent } from './Components/s-directives/s-directives.component';
import { ADirectivesComponent } from './Components/a-directives/a-directives.component';
import { PipeDemoComponent } from './Components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { CapitalAPipe } from './pipes/capital-a.pipe';
import { ServiceService } from './services/service.service';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { HttpExampleComponent } from './Components/http-example/http-example.component';
import { appRoutes } from './route';
import { RouterModule } from '../../node_modules/@angular/router';
import { NavComponentComponent } from './Components/nav-component/nav-component.component';
import { DirectivesComponent } from './Components/directives/directives.component';
import { HighlightComponent } from './Components/highlight/highlight.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { DetailsComponent } from './Components/details/details.component';
import { AnimalsComponent } from './Components/animals/animals.component';
import { PeopleComponent } from './Components/people/people.component';
import { CapitalFirstLetterPipe } from './pipes/capital-first-letter.pipe';
import { FakeUsersComponent } from './Components/fake-users/fake-users.component';

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
    CapitalAPipe,
    HttpExampleComponent,
    NavComponentComponent,
    DirectivesComponent,
    HighlightComponent,
    ProfileComponent,
    DetailsComponent,
    AnimalsComponent,
    PeopleComponent,
    CapitalFirstLetterPipe,
    FakeUsersComponent
  ],
  imports: [ //external modules 
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ServiceService], //Services 
  bootstrap: [AppComponent] //designates root component
})
export class AppModule { }

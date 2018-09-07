import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FirstComponent } from './components/first/first.component';
import { DatabindComponent } from './components/databind/databind.component';

import { FormsModule } from '@angular/forms';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { CapitalizerPipe } from './pipes/capitalizer.pipe';
import { PostService } from './services/post.service';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { RouterModule } from '../../node_modules/@angular/router';

import { appRoutes } from './routes';
import { NavComponent } from './components/nav/nav.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { AssignmentComponent } from './components/assignment/assignment.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';// Need to import the routes

@NgModule({
  declarations: [// Include any components one will use here
	AppComponent, 
	FirstComponent, 
	DatabindComponent, 
	FavoriteComponent, 
	ClickerComponent, 
	SDirectivesComponent, 
	ADirectivesComponent, 
	PipeDemoComponent, 
	ConvertToSpacePipe, 
	CapitalizerPipe, 
	HttpExampleComponent, 
	NavComponent, 
	DirectivesComponent, 
	HighlightComponent, AssignmentComponent, PostDetailsComponent,
  ],
  imports: [ // External Modules
	BrowserModule,
	FormsModule,
	HttpClientModule,
	RouterModule.forRoot(appRoutes)
  ],
  providers: [PostService], // Services
  bootstrap: [AppComponent] // designates root component
})
export class AppModule { }

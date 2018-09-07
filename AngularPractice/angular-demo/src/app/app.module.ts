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
    CapitalizeFirstLetterPipe
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

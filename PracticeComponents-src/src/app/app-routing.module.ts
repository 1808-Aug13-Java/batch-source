import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HighlightComponent } from './components/highlight/highlight.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';


const routes: Routes = [
  // {path: , component: }
  //no '/path'. Just 'path'
  {path: 'highlight', component: HighlightComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'select', component: SelectComponent},
  {path: 'tab', component: TableComponent}
]

@NgModule({
  imports: [
    // CommonModule
    RouterModule.forRoot(routes) //routes is wat we're def
              //in this class. We give it to the root
              //of RouterModule (import it)
  ],
  // declarations: []
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }

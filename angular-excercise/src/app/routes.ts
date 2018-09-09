import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

@NgModule({
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

import { HighlightComponent }      from './highlight/highlight.component';
import { ProfileComponent } from './profile/profile.component';
import { SelectComponent } from './select/select.component';
import { TableComponent } from './table/table.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'highlight', component: HighlightComponent },

  {path: 'profile', component: ProfileComponent},

  {path: 'select', component: SelectComponent},

  {path: 'table', component: TableComponent},

  {path: 'user', component: UserComponent}
]
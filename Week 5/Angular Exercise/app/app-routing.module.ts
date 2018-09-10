import {  Routes } from '@angular/router';
import { Component5Component } from './component5/component5.component';
import { Component4Component } from './component4/component4.component'
import { Component3Component } from './component3/component3.component'

import { Component2Component } from './component2/component2.component'

import { Component1Component } from './component1/component1.component'

export const routes: Routes = [ {

  path: 'one',
  component: Component1Component


}, {
  path: 'two',
  component: Component2Component

},
{
  path: 'three',
  component: Component3Component

},
{
  path: 'four',
  component: Component4Component

},
{
  path: 'five',
  component: Component5Component

},
{
  path: '**',
  pathMatch: 'full',
  redirectTo: ''

},
];


 
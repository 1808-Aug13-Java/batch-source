import {Routes } from '@angular/router'
import { FirstComponent } from './components/first/first.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { DatabindComponent } from './components/databind/databind.component';

export const appRoutes:Routes =[
{
    path:'/first',
    component:FirstComponent

},{

path:'a-directives',
component:ADirectivesComponent

},{
    path:'clicker',
    component:ClickerComponent
},{
    path:'posts',
    component:HttpExampleComponent
},{
    path:'pipes',
    component:PipeDemoComponent
    },{
        path:'s-directives',
        component:SDirectivesComponent
    },{
        path:'databind',
        component:DatabindComponent
    },{
        path:'**',
        pathMatch:'full',
        redirectTo:'' // redirects to local host:4200

    }
]



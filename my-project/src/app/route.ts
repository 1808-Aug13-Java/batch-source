import { Routes } from '@angular/router';
import { FirstComponent } from './Components/first/first.component';
import { ClickerComponent } from './Components/clicker/clicker.component';
import { ADirectivesComponent } from './Components/a-directives/a-directives.component';
import { HttpExampleComponent } from './Components/http-example/http-example.component';
import { PipeDemoComponent } from './Components/pipe-demo/pipe-demo.component';
import { SDirectivesComponent } from './Components/s-directives/s-directives.component';
import { DatabindComponent } from './Components/databind/databind.component';
import { HighlightComponent } from './Components/highlight/highlight.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { DetailsComponent } from './Components/details/details.component';
import { Component } from '../../node_modules/@angular/core';
import { AnimalsComponent } from './Components/animals/animals.component';
import { PeopleComponent } from './Components/people/people.component';
import { FakeUsersComponent } from './Components/fake-users/fake-users.component';


export const appRoutes: Routes = [{

        path: 'first',
        component: FirstComponent
},{
    path: 'a-directives',
    component: ADirectivesComponent
},{
    path: 'clicker',
    component: ClickerComponent
},{
    path: 'posts',
    component: HttpExampleComponent
},{
    path: 'pipes',
    component: PipeDemoComponent
},{
    path: 'databind',
    component: DatabindComponent
},{
    path: 'highlight',
    component: HighlightComponent
},{
    path: 'profile',
    component: ProfileComponent
},{
    path: 'posts/:id',
    component: DetailsComponent
},{
    path: 'animals',
    component: AnimalsComponent
},{
    path: 'people',
    component: PeopleComponent
},{
    path: 'fakeUsers',
    component: FakeUsersComponent
},{
    path:'**',
    pathMatch: 'full',
    redirectTo: ''
}
]

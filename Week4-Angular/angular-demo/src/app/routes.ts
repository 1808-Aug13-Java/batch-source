import { Routes } from '@angular/router';
import { FirstComponent } from './components/first/first.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { DatabindComponent } from './components/databind/databind.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';

export const appRoutes: Routes = [
    {
        path: 'first',
        component: FirstComponent
    },{
        path: 'directives',
        component: DirectivesComponent,
        children: [
            {
                path: 'structural',
                component: SDirectivesComponent
            },{
                path: 'attribute',
                component: ADirectivesComponent
            }
        ]
    },{
        path: 'clicker',
        component: ClickerComponent
    },{
        path: 'posts',
        component: HttpExampleComponent
    },{
        path: 'posts/:id',
        component: PostDetailsComponent
    },{
        path: 'pipes',
        component: PipeDemoComponent
    },{
        path: 'databind',
        component: DatabindComponent
    },{
        path: '**',
        pathMatch: 'full',
        redirectTo: ''
    }

]
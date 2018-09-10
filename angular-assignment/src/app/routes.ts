import { Routes } from '@angular/router';
import { HighlighterComponentComponent } from './components/highlighter-component/highlighter-component.component';
import { ProfileComponentComponent } from './components/profile-component/profile-component.component';
import { SelectComponentComponent } from './components/select-component/select-component.component';
import { TableComponentComponent } from './components/table-component/table-component.component';
import { UserComponentComponent } from './components/user-component/user-component.component';
export const appRoutes: Routes = [
    {
        path: 'highlight',
        component: HighlighterComponentComponent
    },{
        path: 'profile',
        component: ProfileComponentComponent,
    },{
        path: 'select',
        component: SelectComponentComponent
    },{
        path: 'table',
        component: TableComponentComponent
    },{
        path: 'user',
        component: UserComponentComponent
    },{
        path: '**',
        pathMatch: 'full',
        redirectTo: ''
    }
    
];
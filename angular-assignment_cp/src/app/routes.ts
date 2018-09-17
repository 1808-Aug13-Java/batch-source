import { Routes } from '@angular/router';
import { HighlightComponent } from './highlight/highlight.component';
import { ProfileComponent } from './profile/profile.component';
import { SelectComponent } from './select/select.component';
import { TableComponent } from './table/table.component';
import { UsersComponent } from './users/users.component';

export const appRoutes: Routes = [
    {
        path: 'highlight',
        component: HighlightComponent
    },{
        path: 'profile',
        component: ProfileComponent,
    },{
        path: 'select',
        component: SelectComponent
    },{
        path: 'table',
        component: TableComponent
    },{
        path: 'users',
        component: UsersComponent
    },{
        path: '**',
        pathMatch: 'full',
        redirectTo: ''
    }
    
];
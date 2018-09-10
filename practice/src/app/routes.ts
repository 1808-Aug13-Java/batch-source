import { Routes } from '@angular/router';
import { HighlightComponent } from "./components/highlight/highlight.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';
import { UserComponent } from './components/user/user.component';

export const appRoutes: Routes = [
    {
        path: 'highlight',
        component: HighlightComponent
    },{
        path: 'profile',
        component: ProfileComponent
    },{
        path: 'select',
        component: SelectComponent
    },{
        path: 'table',
        component: TableComponent
    },{
        path: 'user',
        component: UserComponent
    },{
        path: '**',
        pathMatch: 'full',
        redirectTo: ''
    }
]
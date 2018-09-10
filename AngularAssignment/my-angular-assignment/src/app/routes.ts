import { Routes } from '@angular/router';
import { HighlightComponent } from './components/highlight/highlight.component';
import { ProfilesComponent } from './components/profiles/profiles.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';

export const appRoutes: Routes = [
    {
        path: 'highlight',
        component: HighlightComponent
    },
    {
        path: 'profile',
        component: ProfilesComponent
    },
    {
        path: 'select',
        component: SelectComponent
    },
    {
        path: 'table',
        component: TableComponent
    },
    {
        path: '**',
        pathMatch: 'full',
        redirectTo: ''
    }
];
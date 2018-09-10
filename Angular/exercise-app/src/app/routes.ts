import { ApiCallComponent } from './components/api-call/api-call.component';
import { SelectComponent } from './components/select/select.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { Routes } from '@angular/router';
import { TableComponent } from './components/table/table.component';

export const appRoutes: Routes = [
    {
        path: 'highlight',
        component: HighlightComponent
    }, {
        path: 'profile',
        component: ProfileComponent
    }, {
        path: 'select',
        component: SelectComponent
    }, {
        path: 'table',
        component: TableComponent
    }, {
        path: 'apiCall',
        component: ApiCallComponent
    }
]
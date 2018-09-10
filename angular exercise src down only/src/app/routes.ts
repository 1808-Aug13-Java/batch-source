import { HighlightComponentComponent } from "./components/highlight-component/highlight-component.component";
import { ProfileComponentComponent } from "./components/profile-component/profile-component.component";
import { SelectComponent } from "./components/select/select.component";
import { TableComponent } from "./components/table/table.component";
import { UserComponent } from "./components/user/user.component";
import { Routes } from "@angular/router";

export const appRoutes: Routes = [
    {
        path: 'highlight-component',
        component: HighlightComponentComponent
    },{
        path: 'profile-component',
        component: ProfileComponentComponent
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
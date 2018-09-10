import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ListComponent } from './components/list/list.component';
import { DetailsComponent } from './components/details/details.component';

// An array of Routes, which contains a Path, and a Component
// This is used to route different components/ display different 
// components based on the routeLink. 
export const appRoutes: Routes = [
	{
		path: 'dashboard',
		component: DashboardComponent,
		// children: [
		// 	{
		// 		path:'details',
		// 		component: DetailsComponent
		// 	}
		// ]
	}, 
	{
		path: 'list',
		component: ListComponent,
		// children: [
		// 	{
		// 		path:'details',
		// 		component: DetailsComponent
		// 	}, 
		// 	{
		// 		path:'details/:id',
		// 		component: DetailsComponent
		// 	}
		// ]
	}, 
	{
		path:'details/:id',
		component: DetailsComponent
	},
	{
		path: '**',
		pathMatch: 'full',
		redirectTo: ''
	}
];
import { Routes } from '@angular/router';
import { FirstComponent } from './components/first/first.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { HttpExampleComponent } from './components/http-example/http-example.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { DatabindComponent } from './components/databind/databind.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { AssignmentComponent } from './components/assignment/assignment.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';

// An array of Routes, which contains a Path, and a Component
// This is used to rout different components/ display different 
// components based on the routeLink. 
export const appRoutes: Routes = [
	{
		path: 'assignment',
		component: AssignmentComponent,
		children: [
			{
				path: 'highlight',
				component: HighlightComponent
			}, 
			{
				path: 'profile',
				component: ProfileComponent
			}, 
			{
				path: 'select',
				component: SelectComponent
			},
			{
				path: "table",
				component: TableComponent
			}
		]
	},
	{
		path: 'first',
		component: FirstComponent
	},
	{
		path: 'posts',
		component: HttpExampleComponent
	}, 
	{
		path: 'posts/:id',
		component: PostDetailsComponent
	}, 
	{
		path: 'clicker',
		component: ClickerComponent
	},
	{
		path: 'pipe',
		component: PipeDemoComponent
	}, 
	{
		path: 'databind',
		component: DatabindComponent
	},
	{
		path: 'directives',
		component: DirectivesComponent,
		children: [
			{
				path: 'structural',
				component: SDirectivesComponent
			}, 
			{
				path: 'attribute',
				component: ADirectivesComponent
			}
		]
	},
	{
		path: '**',
		pathMatch: 'full',
		redirectTo: ''
	}
]

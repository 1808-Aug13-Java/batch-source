import { Routes } from "@angular/router";
import { HighlightTextComponent } from "./highlight-text/highlight-text.component";
import { ProfileComponent } from "./profile/profile.component";
import { SelectButtonsComponent } from "./select-buttons/select-buttons.component";
import { UserTableComponent } from "./user-table/user-table.component";
import { UserHttpComponent } from "./user-http/user-http.component";

export const appRoutes: Routes = [
  {
    path: "highlight",
    component: HighlightTextComponent
  },
  {
    path: "profile",
    component: ProfileComponent
  },
  {
    path: "lists",
    component: SelectButtonsComponent
  },
  {
    path: "userTable",
    component: UserTableComponent
  },
  {
    path: "users",
    component: UserHttpComponent
  },
  {
    path: "**",
    pathMatch: "full",
    redirectTo: ""
  }
];

import {Routes, RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {AuthComponent} from "./auth.component";

export const AUTH_ROUTES: Routes = [
  { path: 'auth', component: AuthComponent, pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forChild(AUTH_ROUTES)],
  exports: [RouterModule]
})
export class AuthRoutingModule {

}

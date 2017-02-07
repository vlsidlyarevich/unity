import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { SignupComponent } from "./signup.component";

export const SIGNUP_ROUTES: Routes = [
  { path: 'signup', component: SignupComponent, pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forChild(SIGNUP_ROUTES)],
  exports: [RouterModule]
})
export class SignupRoutingModule {

}

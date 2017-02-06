import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import {AuthGuard} from "./guards/auth.guard";

export const APP_ROUTES: Routes = [
  { path: '', redirectTo: 'home', pathMatch: "full", canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}

import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { MyProfileComponent } from "./my-profile.component";
import { AuthGuard } from "../../guards/auth.guard";

export const MY_PROFILE_ROUTES: Routes = [
  { path: 'profile', component: MyProfileComponent, pathMatch: 'full', canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(MY_PROFILE_ROUTES)],
  exports: [RouterModule]
})
export class MyProfileRoutingModule {

}

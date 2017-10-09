import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { AuthGuard } from "../../guards/auth.guard";
import { AnalyzeSelectComponent } from "./components/analyze-select/analyze-select-modal.component";

export const HOME_ROUTES: Routes = [
  { path: 'dashboard', component: HomeComponent, pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'analyze/select', component: AnalyzeSelectComponent, pathMatch: 'full', canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(HOME_ROUTES)],
  exports: [RouterModule]
})
export class HomeRoutingModule {

}

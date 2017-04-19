import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { GitAnalyzeComponent } from "./git-analyze.component";
import { AuthGuard } from "../../guards/auth.guard";
import { GitDashboardComponent } from "./components/git-dashboard/git-dashboard.component";

export const GIT_ANALYZE_ROUTES: Routes = [
  { path: 'git', component: GitAnalyzeComponent, pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'git/:login', component: GitDashboardComponent, pathMatch: 'full', canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(GIT_ANALYZE_ROUTES)],
  exports: [RouterModule]
})
export class GitAnalyzeRoutingModule {

}

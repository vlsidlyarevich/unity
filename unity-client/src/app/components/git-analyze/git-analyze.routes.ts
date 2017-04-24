import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { GitAnalyzeComponent } from "./git-analyze.component";
import { AuthGuard } from "../../guards/auth.guard";
import { GitProfileComponent } from "./components/git-profile/git-profile.component";
import { GitRepositoriesComponent } from "./components/git-repositories/git-repositories.component";
import { GitTechnologiesComponent } from "./components/git-technologies/git-technologies.component";

export const GIT_ANALYZE_ROUTES: Routes = [
  {
    path: 'git/:login', component: GitAnalyzeComponent,
    children: [
      { path: '', redirectTo: 'overview', pathMatch: 'full', canActivate: [AuthGuard] },
      { path: 'overview', component: GitProfileComponent, pathMatch: 'full', canActivate: [AuthGuard] },
      { path: 'repositories', component: GitRepositoriesComponent, pathMatch: 'full', canActivate: [AuthGuard] },
      { path: 'technologies', component: GitTechnologiesComponent, pathMatch: 'full', canActivate: [AuthGuard] },]
  }
];

@NgModule({
  imports: [RouterModule.forChild(GIT_ANALYZE_ROUTES)],
  exports: [RouterModule]
})
export class GitAnalyzeRoutingModule {

}

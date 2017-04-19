import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { GitAnalyzeRoutingModule } from "./git-analyze.routes";
import { GitAnalyzeComponent } from "./git-analyze.component";
import { GitDashboardComponent } from "./components/git-dashboard/git-dashboard.component";

@NgModule({
  imports: [
    CommonModule,
    GitAnalyzeRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [GitAnalyzeComponent, GitDashboardComponent]
})
export class GitAnalyzeModule {
}

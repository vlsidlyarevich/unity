import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { GitAnalyzeRoutingModule } from "./git-analyze.routes";
import { GitAnalyzeComponent } from "./git-analyze.component";
import { GitProfileComponent } from "./components/git-profile/git-profile.component";
import { GitRepositoriesComponent } from "./components/git-repositories/git-repositories.component";
import { ChartsModule } from "ng2-charts";
import { GitTechnologiesComponent } from "./components/git-technologies/git-technologies.component";
import { PipeModule } from "../../pipes/pipe-module/pipe.module";

@NgModule({
  imports: [
    CommonModule,
    GitAnalyzeRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ChartsModule,
    PipeModule
  ],
  declarations: [GitAnalyzeComponent, GitProfileComponent, GitRepositoriesComponent, GitTechnologiesComponent]
})
export class GitAnalyzeModule {
}

import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { HomeComponent } from "./home.component";
import { HomeRoutingModule } from "./home.routes";
import { AnalyzeSelectComponent } from "./components/analyze-select/analyze-select-modal.component";

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  declarations: [HomeComponent, DashboardComponent, AnalyzeSelectComponent]
})
export class HomeModule {
}

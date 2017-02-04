import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { HomeComponent } from "./home.component";
import { HomeRoutingModule } from "./home.routes";

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  declarations: [DashboardComponent, HomeComponent]
})
export class HomeModule {
}

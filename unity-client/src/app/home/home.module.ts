import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HomeComponent} from "./home.component";

@NgModule({
  imports: [
    CommonModule,
    HomeComponent
  ],
  declarations: [DashboardComponent, HomeComponent]
})
export class HomeModule {
}

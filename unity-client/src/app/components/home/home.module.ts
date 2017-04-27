import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home.routes';
import { AnalyzeSelectModalComponent } from './components/analyze-select-modal/analyze-select-modal.component';

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  entryComponents: [
    AnalyzeSelectModalComponent
  ],
  declarations: [HomeComponent, DashboardComponent, AnalyzeSelectModalComponent]
})
export class HomeModule {
}

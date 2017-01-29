import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home.routes';
import { DashboardComponent } from './components/dashboard/dashboard.component';

@NgModule({
  imports: [
    CommonModule,

    HomeRoutingModule
  ],
  declarations: [HomeComponent, DashboardComponent]
})
export class HomeModule { }

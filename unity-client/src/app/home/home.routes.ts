import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
    {
        path: 'home', component: HomeComponent, canActivate: [],
        children: [
            { path: 'dashboard', component: DashboardComponent, canActivate: [] },
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {

}

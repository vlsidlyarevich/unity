import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { TopnavbarComponent } from './components/menu/topnavbar/topnavbar.component';
import { SidebarComponent } from './components/menu/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    TopnavbarComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

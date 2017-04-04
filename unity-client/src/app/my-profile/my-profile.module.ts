import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyProfileRoutingModule } from "./my-profile.routes";
import { MyProfileComponent } from "./my-profile.component";

@NgModule({
  imports: [
    CommonModule,
    MyProfileRoutingModule
  ],
  declarations: [MyProfileComponent]
})
export class MyProfileModule {
}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyProfileRoutingModule } from "./my-profile.routes";
import { MyProfileComponent } from "./my-profile.component";
import { MyProfileFormComponent } from './components/my-profile-form/my-profile-form.component';

@NgModule({
  imports: [
    CommonModule,
    MyProfileRoutingModule
  ],
  declarations: [MyProfileComponent, MyProfileFormComponent]
})
export class MyProfileModule {
}

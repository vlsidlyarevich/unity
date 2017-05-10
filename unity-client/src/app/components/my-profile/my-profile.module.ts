import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MyProfileRoutingModule} from './my-profile.routes';
import {MyProfileComponent} from './my-profile.component';
import {MyProfileFormComponent} from './components/my-profile-form/my-profile-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  imports: [
    CommonModule,
    MyProfileRoutingModule,
    ReactiveFormsModule,
    BrowserModule,
    FormsModule
  ],
  declarations: [MyProfileComponent, MyProfileFormComponent]
})
export class MyProfileModule {
}

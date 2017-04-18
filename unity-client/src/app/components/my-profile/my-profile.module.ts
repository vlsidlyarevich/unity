import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyProfileRoutingModule } from './my-profile.routes';
import { MyProfileComponent } from './my-profile.component';
import { MyProfileFormComponent } from './components/my-profile-form/my-profile-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from "@angular/platform-browser";
import { ModalModule } from 'angular2-modal';
import { BootstrapModalModule } from 'ng2-bootstrap-modal';
import { MyProfileEditComponent } from './components/my-profile-edit/my-profile-edit.component';

@NgModule({
  imports: [
    CommonModule,
    MyProfileRoutingModule,
    ReactiveFormsModule,
    BrowserModule,
    FormsModule,
    ModalModule,
    BootstrapModalModule
  ],
  declarations: [MyProfileComponent, MyProfileFormComponent, MyProfileEditComponent]
})
export class MyProfileModule {
}

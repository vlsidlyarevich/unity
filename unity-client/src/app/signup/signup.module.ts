import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { SignupRoutingModule } from "./signup.routes";
import { SignupComponent } from "./signup.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    SignupRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [SignupComponent, SignupFormComponent, SignupFormComponent]
})
export class SignupModule { }

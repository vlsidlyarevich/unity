import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { SignupRoutingModule } from "./signup.routes";
import { SignupComponent } from "./signup.component";
import { FormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    SignupRoutingModule,
    FormsModule
  ],
  declarations: [SignupComponent, SignupFormComponent, SignupFormComponent]
})
export class SignupModule { }

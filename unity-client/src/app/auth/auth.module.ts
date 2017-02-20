import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from "./auth.routes";
import { AuthComponent } from "./auth.component";
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AuthComponent, AuthFormComponent]
})
export class AuthModule {
}

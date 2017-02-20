import { Component, OnInit } from '@angular/core';
import { User } from "../../../models/user";
import { Router } from "@angular/router";
import { SignupService } from "../../../services/SignupService";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { error } from "util";

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
  user: FormGroup;
  loading = false;
  error = '';

  constructor(private router: Router,
              private signupService: SignupService) {
  }

  ngOnInit() {
    this.user = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(4)]),
      password: new FormControl('', [Validators.required,
        Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$")])
    });
  }

  onSubmit() {
    this.loading = true;
    this.signupService.signup(new User(this.user.value.username, this.user.value.password))
      .subscribe(result => {
          if (result === true) {
            this.router.navigate(['/auth']);
          } else {
            this.error = 'Unable to register a new user';
            this.loading = false;
          }
        },
        error => {
          this.error = 'Unable to register a new user';
          this.loading = false;
        });
  }
}

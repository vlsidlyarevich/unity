import { Component, OnInit } from '@angular/core';
import { User } from "../../../models/user";
import { Router } from "@angular/router";
import { SignupService } from "../../../services/SignupService";
import { FormControl, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
  user;
  loading = false;
  error = '';

  constructor(private router: Router,
              private signupService: SignupService) {
  }

  ngOnInit() {
    this.user = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    });
  }

  signup() {
    this.loading = true;
    this.signupService.signup(this.user)
      .subscribe(result => {
        if (result === true) {
          this.router.navigate(['/auth']);
        } else {
          this.error = 'Unable to register a new user';
          this.loading = false;
        }
      });
  }
}

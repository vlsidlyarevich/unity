import { Component, OnInit } from '@angular/core';
import { User } from "../../../models/user";
import { Router } from "@angular/router";
import { SignupService } from "../../../services/SignupService";

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
  signupModel: User = new User();
  loading = false;
  error = '';

  constructor(private router: Router,
              private signupService: SignupService) {
  }

  ngOnInit() {
  }

  signup() {
    this.loading = true;
    this.signupService.signup(this.signupModel)
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

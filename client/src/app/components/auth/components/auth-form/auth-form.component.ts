import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from "../../../../services/AuthenticationService";
import { Credentials } from "../../../../models/credentials";

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.css']
})
export class AuthFormComponent implements OnInit {
  user: FormGroup;
  loading = false;
  error = '';

  constructor(private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.user = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
    this.authService.logout();
  }

  onSubmit() {
    this.loading = true;
    this.authService.login(new Credentials(this.user.value.username, this.user.value.password))
      .subscribe(result => {
          if (result === true) {
            this.router.navigate(['/home']);
          } else {
            this.error = 'Authentification error';
            this.loading = false;
          }
        },
        error => {
          this.error = 'Authentification error';
          this.loading = false;
        });
  }
}

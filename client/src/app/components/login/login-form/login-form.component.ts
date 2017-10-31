import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../../../services/authentication.service";
import { ActivatedRoute, Router } from "@angular/router";
import { FormControl, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  credentials: FormGroup;

  loading = false;

  constructor(private authenticationService: AuthenticationService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.credentials = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.credentials.value.username, this.credentials.value.password)
      .subscribe(result => {
          if (result === true) {
            this.router.navigate(['/404']);
          } else {
            this.loading = false;
          }
        },
        error => {
          this.loading = false;
        });
  }
}

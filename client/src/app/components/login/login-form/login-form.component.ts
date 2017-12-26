import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  private credentials: FormGroup;
  private formBuilder: FormBuilder;
  private loading = false;

  constructor(private authenticationService: AuthenticationService,
              private route: ActivatedRoute,
              private router: Router) {
    this.formBuilder = new FormBuilder();
  }

  ngOnInit() {
    this.credentials = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      rememberMe: [false]
    });
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.credentials.value.username, this.credentials.value.password, this.credentials.value.rememberMe)
      .subscribe(result => {
          if (result === true) {
            this.router.navigate(['/']);
          } else {
            this.loading = false;
          }
        },
        error => {
          this.loading = false;
        });
  }
}

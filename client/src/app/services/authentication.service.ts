import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class AuthenticationService {

  private api = 'http://localhost:8080/api/v1/';

  constructor(private http: HttpClient, private router: Router) {
  }

  isLoggedIn(): boolean {
    try {
      return !!localStorage.currentUser;
    } catch (Error) {
      alert(Error.message);
    }
  }

  login(username: string, password: string) {
    return this.http
      .post<JwtResponse>(this.api + 'auth', JSON.stringify({ username: username, password: password }))
      .subscribe(data => {
        if (data && data.token) {
          localStorage.setItem('currentUser', JSON.stringify(data.token));
          return true;
        } else {
          return false;
        }
      }, error => {
        console.log(error);
      });
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }
}

import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import "rxjs/add/operator/map";
import { RequestOptions, Headers } from "@angular/http";
import { Observable } from "rxjs/Observable";

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

  login(username: string, password: string): Observable<boolean> {
    const body = JSON.stringify({ username: username, password: password });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http
      .post<JwtResponse>(this.api + 'auth', body, { headers: headers })
      .map(data => {
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

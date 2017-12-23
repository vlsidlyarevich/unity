import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { LocalStorageService, SessionStorageService } from 'ng2-webstorage';

@Injectable()
export class AuthenticationService {

  private api = 'http://localhost:8080/api/v1/';

  constructor(private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService,
              private http: HttpClient,
              private router: Router) {
  }

  isLoggedIn(): boolean {
    try {
      return this.$localStorage.retrieve('authenticationToken') || this.$sessionStorage.retrieve('authenticationToken');
    } catch (Error) {
      alert(Error.message);
    }
  }

  login(username: string, password: string, rememberMe: boolean): Observable<boolean> {
    const body = JSON.stringify({ username: username, password: password });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http
      .post<JwtResponse>(this.api + 'auth', body, { headers: headers })
      .map(data => {
        if (data && data.token) {
          this.storeAuthenticationToken(JSON.stringify(data.token), rememberMe);
          return true;
        } else {
          return false;
        }
      }, error => {
        console.log(error);
      });
  }

  loginWithToken(jwt, rememberMe) {
    if (jwt) {
      this.storeAuthenticationToken(jwt, rememberMe);
      return Promise.resolve(jwt);
    } else {
      return Promise.reject('Login error');
    }
  }

  storeAuthenticationToken(jwt, rememberMe) {
    if (rememberMe) {
      this.$localStorage.store('authenticationToken', jwt);
    } else {
      this.$sessionStorage.store('authenticationToken', jwt);
    }
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      this.$localStorage.clear('authenticationToken');
      this.$sessionStorage.clear('authenticationToken');
      observer.complete();
    });
  }
}

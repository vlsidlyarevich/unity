import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { LocalStorageService, SessionStorageService } from 'ng2-webstorage';
import { api } from '../app.constants';

@Injectable()
export class AuthenticationService {

  constructor(private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService,
              private http: HttpClient,
              private router: Router) {
  }

  createAuthOptions() {
    const currentToken = JSON.parse(this.$localStorage.retrieve('authenticationToken'));
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    const params = new HttpParams();
    return ({ headers: headers, params: params });
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
      .post<JwtResponse>(api.auth, body, { headers: headers })
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

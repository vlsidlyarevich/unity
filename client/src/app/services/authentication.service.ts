import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { User } from '../models/user.model';
import { UserSocial } from '../models/user-social.model';
import { config } from '../config/config';
import { TokenService } from "./token.service";

@Injectable()
export class AuthenticationService {

  constructor(private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService,
              private tokenService: TokenService,
              private http: HttpClient) {
  }

  isLoggedIn(): boolean {
    try {
      return this.tokenService.containsToken();
    } catch (Error) {
      alert(Error.message);
    }
  }

  login(username: string, password: string, rememberMe: boolean) {
    const body = JSON.stringify({ username: username, password: password });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http
      .post<JwtResponse>(config.authApi, body, { headers: headers, observe: 'response' })
      .map((data) => {
        if (data && data.body.token) {
          try {
            this.tokenService.storeAuthenticationToken(JSON.stringify(data.body.token), rememberMe);
            this.storeCurrentUser();
            this.storeCurrentUserSocial();
            return data;
          } catch (error) {
            console.log(error);
          }
        }
      });
  }

  socialLoginWithToken(jwt, provider, rememberMe): Promise<any> {
    if (jwt) {
      this.tokenService.storeAuthenticationToken(JSON.stringify(jwt), rememberMe);
      this.storeCurrentUser();
      this.storeCurrentUserSocial();
      this.$localStorage.store('provider', provider);
      return Promise.resolve(jwt);
    } else {
      return Promise.reject('Login error');
    }
  }

  private storeCurrentUser() {
    this.http
      .get<User>(config.userApi + '/me')
      .subscribe((response) => {
          this.$localStorage.store('user', response);
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  private storeCurrentUserSocial() {
    const url = config.userSocialApi.replace('${userId}', this.$localStorage.retrieve('user').id);

    this.http
      .get<UserSocial>(url)
      .subscribe((response) => {
        this.$localStorage.store('userSocial', response);
      });
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      this.tokenService.clearAuthenticationToken();
      observer.complete();
    });
  }


  private handleError(err: HttpErrorResponse) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);

    //FIXME
    return Observable.throw(errorMessage);
  }
}

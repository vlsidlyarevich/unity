import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Credentials } from "../models/credentials";

@Injectable()
export class AuthenticationService {
  public token: String;
  private static AUTH = '/auth';

  constructor(private http: Http) {

  }

  login(credentials: Credentials) {
    const body = JSON.stringify(credentials);
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });

    return this.http.post(environment.serverUrl + AuthenticationService.AUTH, body, options)
      .map((response: Response) => {
        const token = response.json() && response.json().token;
        if (token) {
          this.token = token;
          localStorage.setItem('currentUser', JSON.stringify({ username: credentials.username, token: token, password: credentials.password }));
          return true;
        } else {
          return false;
        }
      })
      .catch(AuthenticationService.handleError);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('currentUser');
  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  private static handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText} || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}

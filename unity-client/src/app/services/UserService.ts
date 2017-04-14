import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { User } from "../models/user";

@Injectable()
export class UserService {
  private static USER = '/user';
  private static TOKEN = 'x-auth-token';

  constructor(private http: Http) {

  }

  getCurrentUser(): Observable<User> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });

    return this.http.get(environment.serverUrl + UserService.USER, options)
      .map((response: Response) => {
        return response.json() && response.json().user;
      }).catch(UserService.handleError);
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

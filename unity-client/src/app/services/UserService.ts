import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { User } from "../models/user";

@Injectable()
export class UserService {
  public static USER = '/user';
  private static USER_UPDATE = '/users';

  constructor(private http: Http) {

  }

  getCurrentUser(): Observable<User> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });

    return this.http.get(environment.serverUrl + UserService.USER, options)
      .map((response: Response) => {
        return UserService.extractData(response);
      }).catch(UserService.handleError);
  }

  updateUserData(user: User): Observable<boolean> {
    const id = user.id;
    const body = JSON.stringify(user);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });

    return this.http.put(environment.serverUrl + `${UserService.USER_UPDATE}/${id}`, body, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch(UserService.handleError);
  }

  private static extractData(res: Response) {
    let data = res.json();
    return data || {};
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

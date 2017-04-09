import { Injectable } from "@angular/core";
import { Http, RequestOptions, Response, Headers } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { UserData } from "../models/userData";

@Injectable()
export class ProfileService {
  private static PROFILE = '/profile';
  private static TOKEN = 'x-auth-token';

  constructor(private http: Http) {

  }

  getUserData(): Observable<UserData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });

    return this.http.get(environment.serverUrl + ProfileService.PROFILE, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch(ProfileService.handleError);
  }

  updateUserData(userData: UserData): Observable<boolean> {
    const body = JSON.stringify(userData);
    const headers = new Headers({
      'Content-Type': 'application/json',
      TOKEN: JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });

    return this.http.post(environment.serverUrl + ProfileService.PROFILE, body, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch(ProfileService.handleError);
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
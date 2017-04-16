import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { UserSocial } from "../models/userSocial";
import { UserService } from "./UserService";

@Injectable()
export class UserSocialService {
  private static USER_SOCIAL = '/social';

  constructor(private http: Http, private userService: UserService) {

  }

  getUserData(): Observable<UserSocial> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    return this.userService.getCurrentUser().flatMap(
      result => {
        return this.http.get(environment.serverUrl + `${UserService.USER}/${result.id}${UserSocialService.USER_SOCIAL}`, options)
          .map((response: Response) => {
            return UserSocialService.extractData(response);
          }).catch(UserSocialService.handleError);
      }).catch(UserSocialService.handleError);
  }

  updateUserData(userData: UserSocial): Observable<boolean> {
    const body = JSON.stringify(userData);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    return this.userService.getCurrentUser().flatMap(result => this.http.post(environment.serverUrl + `/${result.id}${UserSocialService.USER_SOCIAL}`, body, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch(UserSocialService.handleError))
      .catch(UserSocialService.handleError);
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

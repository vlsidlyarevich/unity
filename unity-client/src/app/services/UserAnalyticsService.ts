import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { UserService } from "./UserService";
import { UserAnalytics } from "../models/userAnalytics";

@Injectable()
export class UserAnalyticsService {
  private static USER_ANALYTICS = '/analytics';

  constructor(private http: Http, private userService: UserService) {

  }

  getUserAnalytics(): Observable<UserAnalytics> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    return this.userService.getCurrentUser().flatMap(
      result => {
        return this.http.get(environment.serverUrl + `${UserService.USER}/${result.id}${UserAnalyticsService.USER_ANALYTICS}`, options)
          .map((response: Response) => {
            return UserAnalyticsService.extractData(response);
          }).catch(UserAnalyticsService.handleError);
      }).catch(UserAnalyticsService.handleError);
  }

  deleteUserAnalyticsById(id: string): Observable<string> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    return this.userService.getCurrentUser().flatMap(
      result => {
        return this.http.delete(environment.serverUrl + `${UserService.USER}/${result.id}${UserAnalyticsService.USER_ANALYTICS}/${id}`, options)
          .map((response: Response) => {
            return UserAnalyticsService.extractData(response);
          }).catch(UserAnalyticsService.handleError);
      }).catch(UserAnalyticsService.handleError);
  }

  deleteAllUserAnalytics(): Observable<string> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    return this.userService.getCurrentUser().flatMap(
      result => {
        return this.http.delete(environment.serverUrl + `${UserService.USER}/${result.id}${UserAnalyticsService.USER_ANALYTICS}/all`, options)
          .map((response: Response) => {
            return UserAnalyticsService.extractData(response);
          }).catch(UserAnalyticsService.handleError);
      }).catch(UserAnalyticsService.handleError);
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

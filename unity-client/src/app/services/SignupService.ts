import { Injectable } from "@angular/core";
import { User } from "../models/user";
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";

@Injectable()
export class SignupService {
  private static SIGNUP = '/signup';

  constructor(private http: Http) {

  }

  signup(userModel: User): Observable<boolean> {
    let body = JSON.stringify(userModel);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    return this.http.post(environment.serverUrl + SignupService.SIGNUP, body, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}

import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable()
export class SignupService {
  private static SIGNUP = '/signup';

  constructor(private http: Http) {

  }

  signup(userModel: User): Observable<boolean> {
    const body = JSON.stringify(userModel);
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });

    return this.http.post(environment.serverUrl + SignupService.SIGNUP, body, options)
      .map((response: Response) => {
        return response.status === 200;
      }).catch(SignupService.handleError);
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

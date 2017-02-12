import { Injectable } from "@angular/core";
import { User } from "../models/user";
import { Http, Response } from "@angular/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";

@Injectable()
export class SignupService {
  public token: String;
  private static SIGNUP = '/signup';

  constructor(private http: Http) {

  }

  signup(userModel: User): Observable<boolean> {
    return this.http.post(environment.serverUrl + SignupService.SIGNUP, userModel)
      .map((response: Response)=> {
        return response.status === 200;
      });
  }
}

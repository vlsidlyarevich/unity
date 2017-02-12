import { Injectable } from "@angular/core";
import { User } from "../models/user";
import { Http } from "@angular/http";

@Injectable()
export class AuthenticationService {
  public token: String;

  constructor(private http:Http){

  }

  login(userModel: User) {

  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }
}

import { Injectable } from "@angular/core";

@Injectable()
export class AuthenticationService {

  isLoggedIn(): boolean {
    try {
      return !!localStorage.currentUser;
    } catch (Error) {
      alert(Error.message);
    }
  }
}

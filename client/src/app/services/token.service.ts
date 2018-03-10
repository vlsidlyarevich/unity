import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';

@Injectable()
export class TokenService {

  constructor(private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService) {
  }

  getAuthToken(): string {
    return JSON.parse(this.$localStorage.retrieve('authenticationToken'));
  }

  containsToken(): boolean {
    try {
      return this.$localStorage.retrieve('authenticationToken') || this.$sessionStorage.retrieve('authenticationToken');
    } catch (Error) {
      alert(Error.message);
    }
  }

  storeAuthenticationToken(jwt, rememberMe) {
    if (rememberMe) {
      this.$sessionStorage.store('authenticationToken', jwt);
    } else {
      this.$localStorage.store('authenticationToken', jwt);
    }
  }

  clearAuthenticationToken() {
    this.$localStorage.clear('authenticationToken');
    this.$sessionStorage.clear('authenticationToken');
  }
}

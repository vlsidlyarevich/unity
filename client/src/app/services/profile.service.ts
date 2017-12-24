import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user.model';
import { UserSocial } from '../models/user-social.model';

@Injectable()
export class ProfileService {

  private api = 'http://localhost:8080/api/v1/';

  constructor(private authService: AuthenticationService,
              private http: HttpClient,
              private router: Router) {
  }

  getUserInfoById(id: string): User {
    //TODO call server
    return null;
  }

  getUserInfo(): User {
    //TODO take from local storage
    return null;
  }

  getUserSocialInfo(): UserSocial {
    //TODO take from local storage
    return null;
  }
}

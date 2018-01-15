import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { LocalStorageService } from 'ng2-webstorage';

@Injectable()
export class AuthenticationTokenInterceptor implements HttpInterceptor {

  constructor(private $localStorage: LocalStorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        'x-auth-token': `${JSON.parse(this.$localStorage.retrieve('authenticationToken'))}`
      }
    });
    return next.handle(request);
  }
}

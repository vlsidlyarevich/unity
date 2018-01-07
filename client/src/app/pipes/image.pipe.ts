import { Pipe, PipeTransform } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';

@Pipe({ name: 'image' })
export class ImagePipe implements PipeTransform {

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  transform(url: string) {
    if (url) {
      const headerParams = {
        'Content-Type': 'application/json',
        'x-auth-token': this.authenticationService.createAuthOptions().headers.get('x-auth-token')
      };
      const headers = new HttpHeaders(headerParams);
      const params = new HttpParams();
      const options = ({ headers: headers, params: params, responseType: 'Blob' as 'json' });

      return this.http.get(url, options)
        .map((blob: Blob) => URL.createObjectURL(blob));
    }
  }
}

import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ImageService {

  constructor(private authenticationService: AuthenticationService,
              private http: HttpClient) {
  }

  getImage(imageUrl: string): Observable<Blob> {
    const headerParams = {
      'Content-Type': 'application/json',
      'x-auth-token': this.authenticationService.createAuthOptions().headers.get('x-auth-token')
    };
    const headers = new HttpHeaders(headerParams);
    const params = new HttpParams();
    const options = ({ headers: headers, params: params, responseType: 'Blob' as 'json' });
    const reader = new FileReader();

    return this.http.get(imageUrl, options)
      .map((blob: Blob) => {
        return blob;
      });
  }
}

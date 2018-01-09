import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ImageService {

  constructor(private authenticationService: AuthenticationService,
              private http: HttpClient) {
  }

  getImageAsBlob(imageUrl: string): Observable<Blob> {
    return this.getImageFromServer(imageUrl)
      .map((blob: Blob) => {
        return blob;
      });
  }

  getImage(imageUrl: string): Observable<any> {
    const reader = new FileReader();

    this.getImageFromServer(imageUrl)
      .subscribe((blob: Blob) => {
        reader.readAsDataURL(blob);
      });

    return Observable.create(observer => {
      reader.onloadend = () => {
        observer.next(reader.result);
        observer.complete();
      };
    });
  }

  private getImageFromServer(imageUrl: string): Observable<any> {
    const headerParams = {
      'Content-Type': 'application/json',
      'x-auth-token': this.authenticationService.createAuthOptions().headers.get('x-auth-token')
    };
    const headers = new HttpHeaders(headerParams);
    const params = new HttpParams();
    const options = ({ headers: headers, params: params, responseType: 'blob' as 'json' });

    return this.http.get(imageUrl, options);
  }
}

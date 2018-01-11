import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { config } from '../config/config';

@Injectable()
export class ImageService {

  constructor(private authService: AuthenticationService,
              private http: HttpClient) {
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

  deleteImage(imageId: string): Observable<boolean> {
    const options = this.authService.createAuthOptions();

    return this.http
      .delete(config.imageApi + '/' + imageId, options)
      .map((response) => {
          return true;
        },
        err => {
          this.handleError(err);
        });
  }

  private getImageFromServer(imageUrl: string): Observable<any> {
    const headerParams = {
      'Content-Type': 'application/json',
      'x-auth-token': this.authService.createAuthOptions().headers.get('x-auth-token')
    };
    const headers = new HttpHeaders(headerParams);
    const params = new HttpParams();
    const options = ({ headers: headers, params: params, responseType: 'blob' as 'json' });

    return this.http.get(imageUrl, options);
  }


  private handleError(err: HttpErrorResponse) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return Observable.throw(errorMessage);
  }
}

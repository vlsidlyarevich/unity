import { Pipe, PipeTransform } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';

@Pipe({ name: 'image' })
export class ImagePipe implements PipeTransform {

  constructor(private http: HttpClient) {
  }

  transform(url: string) {
    if (url) {
      const headerParams = {
        'Content-Type': 'application/json',
      };
      const headers = new HttpHeaders(headerParams);
      const params = new HttpParams();
      const options = ({ headers: headers, params: params, responseType: 'Blob' as 'json' });
      const reader = new FileReader();

      return this.http.get(url, options)
        .subscribe((blob: Blob) => {
          let result = '';

          reader.addEventListener("load", () => {
            result = reader.result;
          }, false);

          reader.readAsDataURL(blob);

          return result;
        });
    }
  }
}

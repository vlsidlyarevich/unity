import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { config } from "../config/config";
import { Observable } from "rxjs/Observable";
import { Analytics } from "../models/analytics.model";
import { AnalysisReport } from "../models/analysis-report.model";

@Injectable()
export class AnalyticsReportsService {

  constructor(private http: HttpClient) {
  }

  getAnalytics() {
    return this.http
      .get<Analytics>(config.analyticsApi)
      .map((response) => {
        return response;
      });
  }

  getAnalyticsReportById(id: string) {
    return this.http
      .get<AnalysisReport>(config.analyticsApi + '/' + id)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
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

import { Component, OnInit } from '@angular/core';
import { LoaderService } from "../../../services/loader.service";
import { AnalysisReport } from "../../../models/analysis-report.model";
import { Router } from "@angular/router";
import { NotificationService } from "../../../services/notification.service";
import { TwitterAnalysisService } from "../../../services/twitter-analytics.service";

@Component({
  selector: 'app-analytics-new-twitter',
  templateUrl: './analytics-new-twitter.component.html',
  styleUrls: ['./analytics-new-twitter.component.css']
})
export class AnalyticsNewTwitterComponent implements OnInit {

  public login: string;

  constructor(private notificationsService: NotificationService,
              private loaderService: LoaderService,
              private twitterAnalysisService: TwitterAnalysisService,
              private router: Router) {
  }

  ngOnInit() {
  }

  public analyze(): void {
    this.loaderService.show();
    this.twitterAnalysisService.analyze(this.login)
      .subscribe((report: AnalysisReport) => {
        this.loaderService.hide();
        this.notificationsService.success('Analyzed with ' + report.analysisTime + ' seconds.');
        this.router.navigate(['analytics/report', report.id]);
      }, error => {
        this.loaderService.hide();
        this.notificationsService.error(error.error.message);
      });
  }
}

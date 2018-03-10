import { Component, OnInit } from '@angular/core';
import { NotificationService } from "../../../services/notification.service";
import { LoaderService } from "../../../services/loader.service";
import { GitAnalyticsService } from "../../../services/git-analytics.service";
import { AnalysisReport } from "../../../models/analysis-report.model";
import { Router } from "@angular/router";

@Component({
  selector: 'app-analytics-new-github',
  templateUrl: './analytics-new-github.component.html',
  styleUrls: ['./analytics-new-github.component.css']
})
export class AnalyticsNewGithubComponent implements OnInit {

  public login: string;

  constructor(private notificationsService: NotificationService,
              private loaderService: LoaderService,
              private gitAnalysisService: GitAnalyticsService,
              private router: Router) {
  }

  ngOnInit() {
  }

  public analyze(): void {
    this.loaderService.show();
    this.gitAnalysisService.analyze(this.login)
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

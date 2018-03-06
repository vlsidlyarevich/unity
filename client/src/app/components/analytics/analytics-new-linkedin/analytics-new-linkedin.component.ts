import { Component, OnInit } from '@angular/core';
import { LoaderService } from "../../../services/loader.service";
import { AnalysisReport } from "../../../models/analysis-report.model";
import { Router } from "@angular/router";
import { NotificationService } from "../../../services/notification.service";
import { LinkedinAnalysisService } from "../../../services/linkedin-analytics.service";

@Component({
  selector: 'app-analytics-new-linkedin',
  templateUrl: './analytics-new-linkedin.component.html',
  styleUrls: ['./analytics-new-linkedin.component.css']
})
export class AnalyticsNewLinkedinComponent implements OnInit {

  public login: string;

  constructor(private notificationsService: NotificationService,
              private loaderService: LoaderService,
              private linkedInAnalysisService: LinkedinAnalysisService,
              private router: Router) {
  }

  ngOnInit() {
  }

  public analyze(): void {
    this.loaderService.show();
    this.linkedInAnalysisService.analyze(this.login)
      .subscribe((report: AnalysisReport) => {
        this.loaderService.hide();
        this.notificationsService.success('Analyzed with ' + report.analysisTime + ' seconds.');
        this.router.navigate(['analytics'], report.id);
      }, error => {
        this.loaderService.hide();
        this.notificationsService.error(error.error.message);
      });
  }
}

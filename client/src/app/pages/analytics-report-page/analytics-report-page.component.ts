import { Component, OnInit } from '@angular/core';
import { AnalysisReport } from "../../models/analysis-report.model";
import { AnalyticsReportsService } from "../../services/analytics-reports.service";
import { ActivatedRoute, Router } from "@angular/router";
import { LoaderService } from "../../services/loader.service";
import { NotificationService } from "../../services/notification.service";

@Component({
  selector: 'app-analytics-report-page',
  templateUrl: './analytics-report-page.component.html',
  styleUrls: ['./analytics-report-page.component.css']
})
export class AnalyticsReportPageComponent implements OnInit {

  private report: AnalysisReport;

  constructor(private analyticsReportsService: AnalyticsReportsService,
              private route: ActivatedRoute,
              private router: Router,
              private loaderService: LoaderService,
              private notificationsService: NotificationService) {

  }

  ngOnInit() {
    this.loaderService.show();
    const reportId = this.route.snapshot.paramMap.get('id');
    this.analyticsReportsService.getAnalyticsReportById(reportId)
      .subscribe((report: AnalysisReport) => {
        this.report = report;
        this.loaderService.hide();
      }, error => {
        this.loaderService.hide();
        this.notificationsService.error(error.error.message);
        this.router.navigate(['404']);
      });
  }
}

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AnalyticsReportsService } from "../../../services/analytics-reports.service";
import { Analytics } from "../../../models/analytics.model";
import { AnalyzedResource } from "../../../models/analyzed-resource.model";
import { AnalysisReport } from "../../../models/analysis-report.model";
import { LoaderService } from "../../../services/loader.service";
import { NotificationService } from "../../../services/notification.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-analytics-reports',
  templateUrl: './analytics-reports.component.html',
  styleUrls: ['./analytics-reports.component.css']
})
export class AnalyticsReportsComponent implements OnInit {

  @Output() openDeleteDialog = new EventEmitter<boolean>();
  @Output() closeDeleteDialog = new EventEmitter<boolean>();

  public showDeleteDialog: boolean = false;

  AnalyzedResource: typeof AnalyzedResource = AnalyzedResource;
  public analytics: Analytics;
  public reportToDelete: AnalysisReport;

  constructor(private analyticsReportsService: AnalyticsReportsService,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
  }

  ngOnInit(): void {
    this.init();
  }

  private init(): void {
    this.analyticsReportsService.getAnalytics().subscribe((analytics: Analytics) => {
      this.analytics = analytics;
    });
  }

  showNewAnalysisModal() {
    //TODO
  }

  showDeleteReportDialog(report: AnalysisReport) {
    this.reportToDelete = report;
    this.showDeleteDialog = true;
    this.openDeleteDialog.emit();
  }

  deleteReport(event) {
    this.analyticsReportsService.deleteAnalyticsReport(this.reportToDelete.id).subscribe(
      response => {
        if (response) {
          this.notificationService.success('Report ' + response + ' is successfully deleted');
          this.init();
        }
      }, (error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          this.notificationService.error(error.error.message);
        }
      });
  }
}

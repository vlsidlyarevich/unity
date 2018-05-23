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
  public showModal: boolean = false;

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

  showNewAnalysisModal(): void {
    this.showModal = true;
  }

  showDeleteReportDialog(report: AnalysisReport): void {
    this.reportToDelete = report;
    this.showDeleteDialog = true;
  }

  deleteReport(event) {
    this.loaderService.show();
    this.analyticsReportsService.deleteAnalyticsReport(this.reportToDelete.id).subscribe(
      response => {
        if (response) {
          this.loaderService.hide();
          this.notificationService.success('Report ' + response + ' is successfully deleted');
          this.showDeleteDialog = false;
          this.init();
        }
      }, (error: HttpErrorResponse) => {
        this.loaderService.hide();
        if (error.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          this.notificationService.error(error.error.message);
        }
      });
  }
}

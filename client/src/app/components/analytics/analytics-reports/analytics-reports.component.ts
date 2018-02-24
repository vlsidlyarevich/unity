import { Component, OnInit } from '@angular/core';
import { AnalyticsReportsService } from "../../../services/analytics-reports.service";
import { Analytics } from "../../../models/analytics.model";
import { AnalyzedResource } from "../../../models/analyzed-resource.model";

@Component({
  selector: 'app-analytics-reports',
  templateUrl: './analytics-reports.component.html',
  styleUrls: ['./analytics-reports.component.css']
})
export class AnalyticsReportsComponent implements OnInit {

  AnalyzedResource: typeof AnalyzedResource = AnalyzedResource;
  public analytics: Analytics;

  constructor(private analyticsReportsService: AnalyticsReportsService) {
    this.analyticsReportsService.getAnalytics().subscribe((analytics: Analytics) => {
      this.analytics = analytics;
    });
  }

  ngOnInit() {
  }

  showNewAnalysisModal() {
    //TODO
  }

  showDeleteReportDialog(id: string) {
    //TODO
  }
}

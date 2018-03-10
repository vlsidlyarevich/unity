import { Component, Input, OnInit } from '@angular/core';
import { AnalysisReport } from "../../../models/analysis-report.model";

@Component({
  selector: 'app-analytics-report-linkedin',
  templateUrl: './analytics-report-linkedin.component.html',
  styleUrls: ['./analytics-report-linkedin.component.css']
})
export class AnalyticsReportLinkedinComponent implements OnInit {

  @Input('report') report: AnalysisReport;

  constructor() { }

  ngOnInit() {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { AnalysisReport } from "../../../models/analysis-report.model";

@Component({
  selector: 'app-analytics-report-twitter',
  templateUrl: './analytics-report-twitter.component.html',
  styleUrls: ['./analytics-report-twitter.component.css']
})
export class AnalyticsReportTwitterComponent implements OnInit {

  @Input('report') report: AnalysisReport;

  constructor() { }

  ngOnInit() {
  }

}

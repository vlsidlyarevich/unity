import { Component, Input, OnInit } from '@angular/core';
import { AnalysisReport } from "../../../models/analysis-report.model";

@Component({
  selector: 'app-analytics-report-github',
  templateUrl: './analytics-report-github.component.html',
  styleUrls: ['./analytics-report-github.component.css']
})
export class AnalyticsReportGithubComponent implements OnInit {

  @Input('report') report: AnalysisReport;

  constructor() {
  }

  ngOnInit() {
  }

}

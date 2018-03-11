import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-github-technologies',
  templateUrl: './github-technologies.component.html',
  styleUrls: ['./github-technologies.component.css']
})
export class GithubTechnologiesComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  public topicsTotalView: any[] = [1000, 400];
  public topicsTotalScheme: string = 'forest';
  public topicsTotalLabel: string = 'Topics total';

  public topicsTotal: any[];

  constructor() {
    this.topicsTotal = [];
  }

  ngOnInit() {
    Object.keys(this.analysisResult.topicsTotal)
      .forEach(key => this.topicsTotal
        .push({ "name": key, "value": this.analysisResult.topicsTotal[key] }));

    this.topicsTotal.sort((a, b) => b.value - a.value)
  }
}

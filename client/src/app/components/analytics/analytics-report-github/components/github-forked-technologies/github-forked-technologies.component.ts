import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-github-forked-technologies',
  templateUrl: './github-forked-technologies.component.html',
  styleUrls: ['./github-forked-technologies.component.css']
})
export class GithubForkedTechnologiesComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  public topicsTotalView: any[] = [1000];
  public topicsTotalScheme: string = 'forest';
  public topicsTotalLabel: string = 'Forked topics total';

  public languagesTotalView: any[] = [1000];
  public languagesTotalScheme: string = 'forest';

  public topicsTotal: any[];
  public languagesTotal: any[];

  constructor() {
    this.topicsTotal = [];
    this.languagesTotal = [];
  }

  ngOnInit() {
    Object.keys(this.analysisResult.forksTopicsTotal)
      .forEach(key => this.topicsTotal
        .push({ "name": key, "value": this.analysisResult.forksTopicsTotal[key] }));

    this.topicsTotal.sort((a, b) => b.value - a.value);

    Object.keys(this.analysisResult.forksLanguagesTotal)
      .forEach(key => this.languagesTotal
        .push({ "name": key, "value": this.analysisResult.forksLanguagesTotal[key] }));

    this.languagesTotal.sort((a, b) => b.value - a.value)
  }
}

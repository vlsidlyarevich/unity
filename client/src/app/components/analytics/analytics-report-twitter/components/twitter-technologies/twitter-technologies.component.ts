import { Component, Input, OnInit } from '@angular/core';
import { TwitterAnalysisResult } from "../../../../../models/twitter-analysis-result.model";

@Component({
  selector: 'app-twitter-technologies',
  templateUrl: './twitter-technologies.component.html',
  styleUrls: ['./twitter-technologies.component.css']
})
export class TwitterTechnologiesComponent implements OnInit {

  @Input('analysisResult') analysisResult: TwitterAnalysisResult;

  public tagsTotalView: any[] = [1000];
  public tagsTotalScheme: string = 'forest';
  public tagsTotalLabel: string = 'Interests total';

  public tagsTotal: any[];

  constructor() {
    this.tagsTotal = [];
  }

  ngOnInit() {
    Object.keys(this.analysisResult.tagsTotal)
      .forEach(key => this.tagsTotal
        .push({ "name": key, "value": this.analysisResult.tagsTotal[key] }));

    this.tagsTotal.sort((a, b) => b.value - a.value);
  }
}

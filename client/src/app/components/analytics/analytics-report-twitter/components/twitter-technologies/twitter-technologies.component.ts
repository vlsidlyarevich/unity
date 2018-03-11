import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-twitter-technologies',
  templateUrl: './twitter-technologies.component.html',
  styleUrls: ['./twitter-technologies.component.css']
})
export class TwitterTechnologiesComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  constructor() {
  }

  ngOnInit() {
  }

}

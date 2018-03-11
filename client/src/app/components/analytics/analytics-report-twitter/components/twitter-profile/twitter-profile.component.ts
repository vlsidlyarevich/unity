import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-twitter-profile',
  templateUrl: './twitter-profile.component.html',
  styleUrls: ['./twitter-profile.component.css']
})
export class TwitterProfileComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  constructor() { }

  ngOnInit() {
  }

}

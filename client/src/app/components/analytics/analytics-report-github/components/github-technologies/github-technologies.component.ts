import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-github-technologies',
  templateUrl: './github-technologies.component.html',
  styleUrls: ['./github-technologies.component.css']
})
export class GithubTechnologiesComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  constructor() {
  }

  ngOnInit() {
  }

}

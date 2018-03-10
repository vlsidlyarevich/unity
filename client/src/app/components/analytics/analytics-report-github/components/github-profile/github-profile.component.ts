import { Component, Input, OnInit } from '@angular/core';
import { GithubAnalysisResult } from "../../../../../models/github-analysis-result.model";

@Component({
  selector: 'app-github-profile',
  templateUrl: './github-profile.component.html',
  styleUrls: ['./github-profile.component.css']
})
export class GithubProfileComponent implements OnInit {

  @Input('analysisResult') analysisResult: GithubAnalysisResult;

  public topSkills: Array<any>;
  public skillsTotal: number;

  constructor() {
    this.topSkills = [];
    this.skillsTotal = 0;
  }

  ngOnInit() {
    this.calculateTopSkills();
  }

  private calculateTopSkills(): void {
    let topSkillsMap = new Map<string, number>();

    Object.keys(this.analysisResult.topicsTotal).forEach(((key) => this.skillsTotal += this.analysisResult.topicsTotal[key]));
    const sortedSkills = this.sortMapByValue(this.analysisResult.topicsTotal);
    if (sortedSkills.size >= 5) {
      Array
        .from(sortedSkills)
        .slice(0, 5)
        .forEach(value => topSkillsMap.set(value[0], value[1]))
    } else {
      Array
        .from(sortedSkills)
        .slice(0, sortedSkills.size)
        .forEach(value => topSkillsMap.set(value[0], value[1]));
    }

    this.topSkills = Array.from(topSkillsMap);
  }

  private sortMapByValue(mapToSort: Map<string, number>): Map<string, number> {
    const map = new Map();
    Object.keys(mapToSort).forEach(key => map.set(key, mapToSort[key]));

    return new Map(
      Array
        .from(map)
        .sort((a, b) => {
          return b[1] - a[1];
        })
    )
  }
}

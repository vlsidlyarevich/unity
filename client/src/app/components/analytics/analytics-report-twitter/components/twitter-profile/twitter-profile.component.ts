import { Component, Input, OnInit } from '@angular/core';
import { TwitterAnalysisResult } from "../../../../../models/twitter-analysis-result.model";

@Component({
  selector: 'app-twitter-profile',
  templateUrl: './twitter-profile.component.html',
  styleUrls: ['./twitter-profile.component.css']
})
export class TwitterProfileComponent implements OnInit {

  @Input('analysisResult') analysisResult: TwitterAnalysisResult;

  public topInterests: Array<any>;
  public interestsTotal: number;

  constructor() {
    this.topInterests = [];
    this.interestsTotal = 0;
  }

  ngOnInit() {
    this.calculateTopSkills();
  }

  private calculateTopSkills(): void {
    let topSkillsMap = new Map<string, number>();

    Object.keys(this.analysisResult.tagsTotal)
      .forEach(((key) => this.interestsTotal += this.analysisResult.tagsTotal[key]));
    const sortedSkills = this.sortMapByValue(this.analysisResult.tagsTotal);
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

    this.topInterests = Array.from(topSkillsMap);
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

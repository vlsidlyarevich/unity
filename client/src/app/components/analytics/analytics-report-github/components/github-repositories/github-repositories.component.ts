import { Component, Input, OnInit } from '@angular/core';
import { GithubRepository } from "../../../../../models/github-repository.model";

@Component({
  selector: 'app-github-repositories',
  templateUrl: './github-repositories.component.html',
  styleUrls: ['./github-repositories.component.css']
})
export class GithubRepositoriesComponent implements OnInit {

  @Input('repositories') repositories: Array<GithubRepository>;

  public sortKey: string = 'stargazersCount';
  public reverse: boolean = true;

  constructor() {
  }

  ngOnInit() {
  }

  public sort(sortKey) {
    this.sortKey = sortKey;
    this.reverse = !this.reverse;
  }
}

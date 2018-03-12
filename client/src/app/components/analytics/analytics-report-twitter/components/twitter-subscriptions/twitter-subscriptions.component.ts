import { Component, Input, OnInit } from '@angular/core';
import { TwitterPopularProfile } from "../../../../../models/twitter-popular-profile.model";
import { GithubRepository } from "../../../../../models/github-repository.model";

@Component({
  selector: 'app-twitter-subscriptions',
  templateUrl: './twitter-subscriptions.component.html',
  styleUrls: ['./twitter-subscriptions.component.css']
})
export class TwitterSubscriptionsComponent implements OnInit {

  @Input('subscriptions') subscriptions: Array<TwitterPopularProfile>;

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

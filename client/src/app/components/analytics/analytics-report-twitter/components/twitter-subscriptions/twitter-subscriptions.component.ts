import { Component, Input, OnInit } from '@angular/core';
import { TwitterPopularProfile } from "../../../../../models/twitter-popular-profile.model";

@Component({
  selector: 'app-twitter-subscriptions',
  templateUrl: './twitter-subscriptions.component.html',
  styleUrls: ['./twitter-subscriptions.component.css']
})
export class TwitterSubscriptionsComponent implements OnInit {

  @Input('subscriptions') repositories: Array<TwitterPopularProfile>;

  constructor() {
  }

  ngOnInit() {
  }

}

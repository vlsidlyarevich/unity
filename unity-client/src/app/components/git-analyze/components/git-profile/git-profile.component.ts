import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { UserAnalyticsService } from "../../../../services/UserAnalyticsService";
import { AnalyzeReport } from "../../../../models/analyzeReport";

@Component({
  selector: 'app-git-profile',
  templateUrl: './git-profile.component.html',
  styleUrls: ['./git-profile.component.css']
})
export class GitProfileComponent implements OnInit {
  error = '';
  report: AnalyzeReport;
  loading = false;

  constructor(private userAnalyticsService: UserAnalyticsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    //FIXME take from store
    this.route.parent.params.subscribe(params => {
      if (params['login']) {
        this.userAnalyticsService.getUserAnalyticsReport(params['id'])
          .subscribe(
            result => {
              this.report = result;
              this.loading = false;
            },
            error => {
              this.error = error;
              this.loading = false;
            }
          )
      }
    });
    // this.profile = this.gitProfileService.profile;
  }
}

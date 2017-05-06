import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { UserAnalyticsService } from "../../../../services/UserAnalyticsService";
import { AnalyzeReport } from "../../../../models/analyzeReport";

@Component({
  selector: 'app-git-repositories',
  templateUrl: './git-repositories.component.html',
  styleUrls: ['./git-repositories.component.css']
})
export class GitRepositoriesComponent implements OnInit {
  error = ' ';
  report: AnalyzeReport;
  loading = false;
  topBy = ' ';
  // searchBy = ' ';

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
  }
}

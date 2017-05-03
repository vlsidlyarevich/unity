import { Component, OnInit } from "@angular/core";
import { DialogService } from "ng2-bootstrap-modal";
import { AnalyzeSelectModalComponent } from "../analyze-select-modal/analyze-select-modal.component";
import { AnalyzeSource } from "../../../../models/analyzeSource";
import { Router } from "@angular/router";
import { UserAnalyticsService } from "../../../../services/UserAnalyticsService";
import { UserAnalytics } from "../../../../models/userAnalytics";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private analyzeSource: AnalyzeSource;
  private userAnalytics: UserAnalytics;
  private error: string;

  constructor(private dialogService: DialogService,
              private userAnalyticsService: UserAnalyticsService,
              private router: Router,) {
  }

  ngOnInit() {
    this.userAnalyticsService.getUserAnalytics().subscribe(
      result => {
        this.userAnalytics = result;
      },
      error => {
        this.error = error;
      }
    )
  }

  showSelect() {
    let disposable = this.dialogService.addDialog(AnalyzeSelectModalComponent, {})
      .subscribe((source) => {
        if (source in AnalyzeSource) {
          this.analyzeSource = source;
          this.navigateToAnalyze();
        }
      });
    //We can close dialog calling disposable.unsubscribe();
    //If dialog was not closed manually close it by timeout
    setTimeout(() => {
      disposable.unsubscribe();
    }, 1000000);
  }

  showSelected(username: string, id: string) {
    this.router.navigate(['analyze/git/' + username + '/' + id]);
  }

  private navigateToAnalyze() {
    switch (this.analyzeSource) {
      case AnalyzeSource.Github:
        this.router.navigate(['analyze/git']);
        break;
      case AnalyzeSource.Facebook:
        this.router.navigate(['analyze/facebook']);
        break;
      case AnalyzeSource.Twitter:
        this.router.navigate(['analyze/twitter']);
        break;
    }
  }
}

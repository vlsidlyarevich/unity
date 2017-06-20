import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserAnalyticsService } from "../../../../services/UserAnalyticsService";
import { UserAnalytics } from "../../../../models/userAnalytics";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private userAnalytics: UserAnalytics;
  private error: string;

  constructor(private userAnalyticsService: UserAnalyticsService,
              private router: Router) {
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
    this.router.navigate(['analyze/select']);
  }

  showSelected(login: string, id: string) {
    this.router.navigate(['analyze/git/' + login + '/' + id]);
  }

  deleteSelected(id: string) {
    this.userAnalyticsService.deleteUserAnalyticsById(id).subscribe((result) => {
      location.reload();
    });
  }
}

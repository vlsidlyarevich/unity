import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../../../../services/AuthenticationService";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private authService: AuthenticationService) {
  }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
  }
}

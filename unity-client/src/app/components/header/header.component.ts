import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/AuthenticationService";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private userName: String;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.userName = JSON.parse(localStorage.getItem('currentUser')).username;
  }

  isUserLoggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }
}

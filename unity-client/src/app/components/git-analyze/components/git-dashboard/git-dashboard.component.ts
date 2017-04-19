import { Component, OnInit } from '@angular/core';
import { GitProfileService } from "../../../../services/GitProfileService";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-git-dashboard',
  templateUrl: './git-dashboard.component.html',
  styleUrls: ['./git-dashboard.component.css']
})
export class GitDashboardComponent implements OnInit {
  error = '';
  gitProfile;
  loading = false;

  constructor(private gitProfileService: GitProfileService, private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.loading = true;
    this.route.params.subscribe(params => {
      if (params['login']) {
        this.gitProfileService.getGitProfileData(params['login'])
          .subscribe(
            result => {
              this.gitProfile = result;
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

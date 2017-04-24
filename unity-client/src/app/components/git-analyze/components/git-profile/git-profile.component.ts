import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { GitProfileService } from "../../../../services/GitProfileService";

@Component({
  selector: 'app-git-profile',
  templateUrl: './git-profile.component.html',
  styleUrls: ['./git-profile.component.css']
})
export class GitProfileComponent implements OnInit {
  error = '';
  gitProfile;
  loading = false;

  constructor(private gitProfileService: GitProfileService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;
    this.route.parent.params.subscribe(params => {
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
    // this.gitProfile = this.gitProfileService.gitProfile;
  }
}

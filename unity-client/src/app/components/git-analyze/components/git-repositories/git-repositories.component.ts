import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { GitProfileService } from "../../../../services/GitProfileService";

@Component({
  selector: 'app-git-repositories',
  templateUrl: './git-repositories.component.html',
  styleUrls: ['./git-repositories.component.css']
})
export class GitRepositoriesComponent implements OnInit {
  error = ' ';
  gitProfile: any;
  loading = false;
  topBy = ' ';
  // searchBy = ' ';

  constructor(private gitProfileService: GitProfileService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    //FIXME take from store
    this.route.parent.params.subscribe(params => {
      if (params['login']) {
        this.gitProfileService.getGitProfileData(params['login'], params['analyzeId'])
          .subscribe(
            result => {
              this.gitProfile = result.reports[0];
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

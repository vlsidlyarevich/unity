import { Component, OnInit } from '@angular/core';
import { GitProfileService } from "../../services/GitProfileService";
import { Router } from "@angular/router";

@Component({
  selector: 'app-git-analyze',
  templateUrl: './git-analyze.component.html',
  styleUrls: ['./git-analyze.component.css']
})
export class GitAnalyzeComponent implements OnInit {
  login: string;
  error: string;
  loading: boolean;

  constructor(private gitProfileService: GitProfileService,
              private router: Router) {
  }

  ngOnInit() {
  }

  analyze() {
    this.loading = true;
    this.gitProfileService.analyzeGitProfileData(this.login)
      .subscribe(
        result => {
          this.router.navigate(['/analyze/git/' + this.login + '/' + result.reports[0].id]);
          this.loading = false;
        },
        error => {
          this.error = error;
          this.loading = false;
        })
  }
}

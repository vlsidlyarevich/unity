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

  constructor(private gitProfileService: GitProfileService,
              private router: Router) {
  }

  ngOnInit() {
  }

  analyze() {

  }
}

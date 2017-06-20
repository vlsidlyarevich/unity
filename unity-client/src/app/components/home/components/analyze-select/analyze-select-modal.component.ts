import { Component } from "@angular/core";
import { AnalyzeSource } from "../../../../models/analyzeSource";
import { Router } from "@angular/router";


@Component({
  selector: 'app-analyze-select',
  templateUrl: './analyze-select.component.html',
  styleUrls: ['./analyze-select.component.css']
})
export class AnalyzeSelectComponent {
  source: AnalyzeSource = AnalyzeSource.Github;

  constructor(private router: Router) {
  }

  select(source: string): void {
    if (source in AnalyzeSource) {
      this.source = AnalyzeSource[source];
    }
  }

  close(): void {
    this.router.navigate(['dashboard']);
  }

  confirm() {
    this.navigateToAnalyze();
  }

  private navigateToAnalyze() {
    switch (this.source) {
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

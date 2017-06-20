import { Component, OnInit } from "@angular/core";
import "chart.js";
import { ActivatedRoute } from "@angular/router";
import { AnalyzeReport } from "../../../../models/analyzeReport";
import { UserAnalyticsService } from "../../../../services/UserAnalyticsService";

@Component({
  selector: 'app-git-technologies',
  templateUrl: './git-technologies.component.html',
  styleUrls: ['./git-technologies.component.css']
})
export class GitTechnologiesComponent implements OnInit {
  error = '';
  report: AnalyzeReport;
  loading = false;
  isDataAvailable: boolean = false;
  languagesMap: { [key: string]: number; } = {};
  topicsMap: { [key: string]: number; } = {};

  constructor(private userAnalyticsService: UserAnalyticsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    //FIXME take from store
    this.route.parent.params.subscribe(params => {
      if (params['analyzeId']) {
        this.userAnalyticsService.getUserAnalyticsReport(params['analyzeId'])
          .subscribe(
            result => {
              this.report = result;
              this.loading = false;
              Promise.all([this.initLanguageChartData(), this.initTopicChartData()]).then(() => {
                this.isDataAvailable = true;
              });
            },
            error => {
              this.error = error;
              this.loading = false;
              this.isDataAvailable = true;
            }
          )
      }
    });
  }

  // languages chart
  public languageChartLabels: string[] = [];
  public languageChartData: number[] = [];
  public languageChartType: string = 'pie';

  // topic chart
  public topicChartLabels: string[] = [];
  public topicChartData: number[] = [];
  public topicChartType: string = 'doughnut';

  // repo creation activity chart
  public lineChartData: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' },
    { data: [18, 48, 77, 9, 100, 27, 40], label: 'Series C' }
  ];
  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'line';

  initLanguageChartData(): Promise<any> {
    this.initLanguagesMap().then(() => {
      this.getLanguagesData().then(result => this.languageChartData = result);
      this.languageChartLabels = Object.keys(this.languagesMap);
    });
    return new Promise((resolve, reject) => {
      resolve()
    })
  }

  initLanguagesMap(): Promise<any> {
    for (let i = 0; i < this.report.result.repos.length; i++) {
      let repo = this.report.result.repos[i];
      for (const language of Object.keys(repo.languages)) {
        if (!this.languagesMap[language]) {
          this.languagesMap[language] = Number.parseInt(repo.languages[language]);
        } else {
          this.languagesMap[language] += Number.parseInt(repo.languages[language]);
        }
      }
    }
    return new Promise((resolve, reject) => {
      resolve()
    })
  }

  getLanguagesData(): Promise<Array<number>> {
    return new Promise((resolve, reject) => {
      let result = [];
      for (const language of Object.keys(this.languagesMap)) {
        result.push(this.languagesMap[language]);
      }
      resolve(result);
    });
  }

  initTopicChartData(): Promise<any> {
    this.initTopicsMap().then(() => {
      this.getTopicsData().then(result => this.topicChartData = result);
      this.topicChartLabels = Object.keys(this.topicsMap);
    });
    return new Promise((resolve, reject) => {
      resolve()
    })
  }

  initTopicsMap(): Promise<any> {
    for (let i = 0; i < this.report.result.repos.length; i++) {
      let repo = this.report.result.repos[i];
      if (repo.topics) {
        for (const topic of repo.topics) {
          if (this.topicsMap[topic]) {
            this.topicsMap[topic] += 1;
          } else {
            this.topicsMap[topic] = 1;
          }
        }
      }
    }
    return new Promise((resolve, reject) => {
      resolve()
    })
  }

  getTopicsData(): Promise<Array<number>> {
    return new Promise((resolve, reject) => {
      let result = [];
      for (const topic of Object.keys(this.topicsMap)) {
        result.push(this.topicsMap[topic]);
      }
      resolve(result);
    });
  }
}

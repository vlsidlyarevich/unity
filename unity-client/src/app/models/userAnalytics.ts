import { AnalyzeReport } from "./analyzeReport";

export class UserAnalytics {
  id: string;
  userId: string;
  reports: Array<AnalyzeReport>;

  constructor(id: string, userId: string, reports: Array<AnalyzeReport>) {
    this.id = id;
    this.userId = userId;
    this.reports = reports;
  }
}

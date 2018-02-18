import { AnalysisReport } from "./analysis-report.model";

export class Analytics {
  constructor() {
  }

  id: string;
  userId: string;
  reports: Array<AnalysisReport>;
}

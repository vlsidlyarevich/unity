import { AnalyzeSource } from "./analyzeSource";

export class AnalyzeReport {
  id: string;
  resource: AnalyzeSource;
  analyzedAt: Date;
  analysisTime: number;
  result: any;

  constructor(id: string, resource: AnalyzeSource, analyzedAt: Date,
              analysisTime: number, result: any) {
    this.id = id;
    this.resource = resource;
    this.analyzedAt = analyzedAt;
    this.analysisTime = analysisTime;
    this.result = result;
  }
}

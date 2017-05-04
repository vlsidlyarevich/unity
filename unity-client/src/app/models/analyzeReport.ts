import { AnalyzeSource } from "./analyzeSource";

export class AnalyzeReport {
  id: string;
  resource: AnalyzeSource;
  analyzedAt: Date;
  analysisTime: number;
  result: object;

  constructor(id: string, resource: AnalyzeSource, analyzedAt: Date,
              analysisTime: number, result: object) {
    this.id = id;
    this.resource = resource;
    this.analyzedAt = analyzedAt;
    this.analysisTime = analysisTime;
    this.result = result;
  }
}

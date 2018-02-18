import { AnalyzedResource } from "./analyzed-resource.model";
import { GithubAnalysisResult } from "./github-analysis-result.model";
import { TwitterAnalysisResult } from "./twitter-analysis-result.model";
import { LinkedinAnalysisResult } from "./linkedin-analysis-result.model";

export class AnalysisReport {
  constructor() {
  }

  id: string;
  resource: AnalyzedResource;
  analyzedAt: string;
  analysisTime: number;
  result: GithubAnalysisResult | TwitterAnalysisResult | LinkedinAnalysisResult;
}

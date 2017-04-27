import { Component } from "@angular/core";
import { DialogComponent, DialogService } from "ng2-bootstrap-modal";
import { AnalyzeSource } from "../../../../models/analyzeSource";


export interface SelectModel {
  source: AnalyzeSource;
}

@Component({
  selector: 'app-analyze-select-modal',
  templateUrl: './analyze-select-modal.component.html',
  styleUrls: ['./analyze-select-modal.component.css']
})
export class AnalyzeSelectModalComponent extends DialogComponent<SelectModel, AnalyzeSource> implements SelectModel {
  source: AnalyzeSource = AnalyzeSource.Github;

  constructor(dialogService: DialogService) {
    super(dialogService);
  }

  confirm() {
    this.result = this.source;
    this.close();
  }

  select(source: string): void {
    if (source in AnalyzeSource) {
      this.source = AnalyzeSource[source];
    }
    console.error(this.source);
  }
}

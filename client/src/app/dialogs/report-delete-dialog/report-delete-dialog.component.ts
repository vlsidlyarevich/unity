import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { animate, style, transition, trigger } from "@angular/animations";
import { AnalysisReport } from "../../models/analysis-report.model";
import { AnalyzedResource } from "../../models/analyzed-resource.model";

@Component({
  selector: 'app-report-delete-dialog',
  templateUrl: './report-delete-dialog.component.html',
  styleUrls: ['./report-delete-dialog.component.css'],
  animations: [
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ])
  ]
})
export class ReportDeleteDialogComponent implements OnInit {
  @Input('report') report: AnalysisReport;
  @Input('visible') visible: boolean;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() deleteEvent: EventEmitter<any> = new EventEmitter<boolean>();

  AnalyzedResource: typeof AnalyzedResource = AnalyzedResource;

  constructor() {
  }

  ngOnInit() {
  }

  close() {
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }

  public delete() {
    this.deleteEvent.emit(true);
    this.visible = false;
  }
}

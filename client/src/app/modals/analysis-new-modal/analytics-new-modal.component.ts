import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { animate, style, transition, trigger } from "@angular/animations";
import { Router } from "@angular/router";

@Component({
  selector: 'app-analytics-new-modal',
  templateUrl: './analytics-new-modal.component.html',
  styleUrls: ['./analytics-new-modal.component.css'],
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
export class AnalyticsNewModalComponent implements OnInit {
  @Input('visible') visible: boolean;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  close(): void {
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }

  selectGithub(): void {
    this.visible = false;
    this.visibleChange.emit(this.visible);
    this.router.navigate(['/analytics/new/github']);
  }

  selectTwitter(): void {
    this.visible = false;
    this.visibleChange.emit(this.visible);
    this.router.navigate(['/analytics/new/twitter']);
  }

  selectLinkedIn(): void {
    this.visible = false;
    this.visibleChange.emit(this.visible);
    this.router.navigate(['/analytics/new/linkedin']);
  }
}

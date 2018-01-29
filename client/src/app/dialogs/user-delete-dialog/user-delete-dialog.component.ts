import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from "../../models/user.model";
import { animate, style, transition, trigger } from "@angular/animations";

@Component({
  selector: 'app-user-delete-dialog',
  templateUrl: './user-delete-dialog.component.html',
  styleUrls: ['./user-delete-dialog.component.css'],
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
export class UserDeleteDialogComponent implements OnInit {
  @Input('visible') visible: boolean;
  @Input('user') user: User;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() deleteEvent: EventEmitter<any> = new EventEmitter<boolean>();

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

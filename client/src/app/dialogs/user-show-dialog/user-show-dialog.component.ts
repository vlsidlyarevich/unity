import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from "../../models/user.model";
import { animate, style, transition, trigger } from "@angular/animations";

@Component({
  selector: 'app-user-show-dialog',
  templateUrl: './user-show-dialog.component.html',
  styleUrls: ['./user-show-dialog.component.css'],
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
export class UserShowDialogComponent implements OnInit {
  @Input('visible') visible: boolean;
  @Input('user') user: User;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {
  }

  ngOnInit() {
  }

  close() {
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }
}

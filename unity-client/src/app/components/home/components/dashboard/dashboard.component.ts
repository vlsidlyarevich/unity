import { Component, OnInit } from "@angular/core";
import { DialogService } from "ng2-bootstrap-modal";
import { AnalyzeSelectModalComponent } from "../analyze-select-modal/analyze-select-modal.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dialogService: DialogService) {
  }

  ngOnInit() {

  }

  showSelect() {
    let disposable = this.dialogService.addDialog(AnalyzeSelectModalComponent, {
      title: 'Confirm title',
      message: 'Confirm message'
    })
      .subscribe((isConfirmed) => {
        //We get dialog result
        if (isConfirmed) {
          alert('accepted');
        }
        else {
          alert('declined');
        }
      });
    //We can close dialog calling disposable.unsubscribe();
    //If dialog was not closed manually close it by timeout
    setTimeout(() => {
      disposable.unsubscribe();
    }, 1000000);
  }
}

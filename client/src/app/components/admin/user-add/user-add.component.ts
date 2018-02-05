import { Component, OnInit } from '@angular/core';
import { LoaderService } from "../../../services/loader.service";
import { User } from "../../../models/user.model";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { UserService } from "../../../services/user.service";
import { NotificationService } from "../../../services/notification.service";

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  public userDataForm: FormGroup;
  private formBuilder: FormBuilder;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
    this.formBuilder = new FormBuilder();
    this.initializeEmptyUserForm();
  }

  ngOnInit() {

  }

  public updateUserData() {
    this.loaderService.show();

    const user: User = {
      id: "",
      authorities: this.userDataForm.value.authorities,
      username: this.userDataForm.value.username,
      password: this.userDataForm.value.password,
      linkedInLoginEnabled: this.userDataForm.value.linkedInLoginEnabled,
      twitterLoginEnabled: this.userDataForm.value.twitterLoginEnabled,
      facebookLoginEnabled: this.userDataForm.value.facebookLoginEnabled,
      accountNonExpired: this.userDataForm.value.accountNonExpired,
      accountNonLocked: this.userDataForm.value.accountNonLocked,
      credentialsNonExpired: this.userDataForm.value.credentialsNonExpired,
      enabled: this.userDataForm.value.enabled,
      createdAt: this.userDataForm.value.createdAt,
      updatedAt: this.userDataForm.value.updatedAt,
    };

    this.userService.saveUser(user)
      .subscribe(user => {
        this.notificationService.success("New user:" + user.id + " created")
      });
    this.loaderService.hide();
  }

  private initializeEmptyUserForm() {
    this.userDataForm = this.formBuilder.group({
      id: [""],
      username: ["", [Validators.required, Validators.minLength(4)]],
      password: ["", [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]],
      authorities: ["", Validators.required],
      facebookLoginEnabled: [false],
      linkedInLoginEnabled: [false],
      twitterLoginEnabled: [false],
    });
  }

  public showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  public resetUserForm() {
    this.initializeEmptyUserForm();
  }
}

import { Component, OnInit } from '@angular/core';
import { NotificationService } from "../../../services/notification.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { User } from "../../../models/user.model";
import { LoaderService } from "../../../services/loader.service";
import { UserService } from "../../../services/user.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  public user: User;
  public userDataForm: FormGroup;
  private id: string;
  private formBuilder: FormBuilder;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
    this.id = this.route.snapshot.paramMap.get('id');
    this.formBuilder = new FormBuilder();
    this.initializeEmptyUserForm();
  }

  ngOnInit() {
    this.userService.getUserInfoById(this.id)
      .subscribe((user: User) => {
        this.user = user;
        this.initializeUserForm();
      });
  }

  public updateUserData() {
    this.loaderService.show();

    const user: User = {
      id: this.id,
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

    this.userService.updateUserInfoById(user, this.id)
      .subscribe(response => {
        this.notificationService.success("User information updated")
      });
    this.loaderService.hide();
  }

  private initializeEmptyUserForm() {
    this.userDataForm = this.formBuilder.group({
      id: [""],
      username: ["", [Validators.required, Validators.minLength(4)]],
      password: ["", [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]],
      facebookLoginEnabled: [false],
      linkedInLoginEnabled: [false],
      twitterLoginEnabled: [false],
    });
  }

  private initializeUserForm() {
    this.userDataForm = this.formBuilder.group({
      id: [this.user.id],
      username: [this.user.username, [Validators.required, Validators.minLength(4)]],
      password: [this.user.password, [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]],
      facebookLoginEnabled: [this.user.facebookLoginEnabled],
      linkedInLoginEnabled: [this.user.linkedInLoginEnabled],
      twitterLoginEnabled: [this.user.twitterLoginEnabled],
    });
  }

  public showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  public getUserAuthority(authorities: string[]): string {
    let result = 'User';
    if (authorities && authorities.find(authority => (authority === `ROLE_ADMIN`))) {
      result = 'Administrator';
    }

    return result;
  }

  public resetUserForm() {
    this.initializeUserForm();
  }
}

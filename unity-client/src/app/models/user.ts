export class User {
  id: String;
  authorities: String[];
  username: String;
  password: String;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  isEnabled: boolean;

  constructor(id: String, authorities: String[], username: String, password: String,
              accountNonExpired: boolean, accountNonLocked: boolean, credentialsNonExpired: boolean,
              isEnabled: boolean,) {
    this.id = id;
    this.authorities = authorities;
    this.username = username;
    this.password = password;
    this.accountNonExpired = accountNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.credentialsNonExpired = credentialsNonExpired;
    this.isEnabled = isEnabled;
  }
}

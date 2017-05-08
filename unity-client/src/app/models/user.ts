export class User {
  id: string;
  authorities: string[];
  username: string;
  password: string;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  enabled: boolean;
  createdAt: string;
  updatedAt: string;

  constructor(id: string, authorities: string[], username: string, password: string,
              accountNonExpired: boolean, accountNonLocked: boolean, credentialsNonExpired: boolean,
              enabled: boolean, createdAt: string, updatedAt: string) {
    this.id = id;
    this.authorities = authorities;
    this.username = username;
    this.password = password;
    this.accountNonExpired = accountNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.credentialsNonExpired = credentialsNonExpired;
    this.enabled = enabled;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}

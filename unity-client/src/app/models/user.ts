export class User {
  id: String;
  authorities: String[];
  username: String;
  password: String;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  isEnabled: boolean;
}

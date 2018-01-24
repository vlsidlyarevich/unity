import { Role } from "./role.model";

export class User {
  constructor() {
  }

  id: string;
  authorities: Array<Role>;
  username: string;
  password: string;
  linkedInLoginEnabled: boolean;
  twitterLoginEnabled: boolean;
  facebookLoginEnabled: boolean;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  enabled: boolean;
  createdAt: string;
  updatedAt: string;
}

export class UserSocial {
  firstName: String;
  lastName: String;
  email: String;
  skype: String;
  image: String;
  additional: String;

  constructor(firstName: String, lastName: String, email: String, skype: String, image: String, additional: String) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.skype = skype;
    this.image = image;
    this.additional = additional;
  }
}

export class UserData {
  username: String;
  password: String;
  firstName: String;
  lastName: String;
  email: String;
  skype: String;
  image: String;
  additional: String;

  constructor(username: String, password: String, firstName: String, lastName: String, email: String, skype: String, image: String, additional: String) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.skype = skype;
    this.image = image;
    this.additional = additional;
  }
}

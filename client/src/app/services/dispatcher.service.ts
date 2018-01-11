import { Subject } from 'rxjs/Subject';

export class Dispatcher {
  static instance: Dispatcher;
  public user = new Subject<any>();
  public userSocial = new Subject<any>();
  public userImage = new Subject<string>();

  static getInstance() {
    if (Dispatcher.instance == null) {
      Dispatcher.instance = new Dispatcher();
    }
    return Dispatcher.instance;
  }

  updateUser(user) {
    this.user.next(user);
  }

  updateUserSocial(userSocial) {
    this.userSocial.next(userSocial);
  }

  updateUserImage(image) {
    this.userImage.next(image);
  }
}

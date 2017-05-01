export class UserAnalytics {
  id: string;
  userId: string;
  analyzedData: Array<any>;

  constructor(id: string, userId: string, analyzedData: Array<any>) {
    this.id = id;
    this.userId = userId;
    this.analyzedData = analyzedData;
  }
}

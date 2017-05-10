export class GitAnalytics {
  id: string;
  userId: string;
  result: any;

  constructor(id: string, userId: string, result: any) {
    this.id = id;
    this.userId = userId;
    this.result = result;
  }
}

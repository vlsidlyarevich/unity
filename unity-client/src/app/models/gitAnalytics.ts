export class GitAnalytics {
  id: string;
  userId: string;
  result: object;

  constructor(id: string, userId: string, result: object) {
    this.id = id;
    this.userId = userId;
    this.result = result;
  }
}

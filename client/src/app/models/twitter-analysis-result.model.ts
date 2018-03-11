import { TwitterPopularProfile } from "./twitter-popular-profile.model";

export class TwitterAnalysisResult {
  constructor() {
  }

  id: string;
  name: string;
  email: string;
  screenName: string;
  location: string;
  description: string;
  profileImageURL: string;
  url: string;
  followersCount: number;
  createdAt: string;
  lang: string;
  isVerified: boolean;
  tagsTotal: Map<string, number>;
  popularProfilesSubscribed: Array<TwitterPopularProfile>;
}

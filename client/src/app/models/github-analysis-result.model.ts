import { GithubRepository } from "./github-repository.model";

export class GithubAnalysisResult {
  constructor() {
  }

  id: number;
  login: string;
  avatarUrl: string;
  gravatarId: string;
  url: string;
  htmlUrl: string;
  gistsUrl: string;
  starredUrl: string;
  subscriptionsUrl: string;
  organizationsUrl: string;
  repos: Array<GithubRepository>;
  languagesTotal: Map<string, number>;
  topicsTotal: Map<string, number>;
  forksLanguagesTotal: Map<string, number>;
  forksTopicsTotal: Map<string, number>;
  type: string;
  name: string;
  company: string;
  blog: string;
  email: string;
  hireable: string;
  bio: string;
  publicRepos: number;
  publicGists: number;
  followers: number;
  following: number;
  createdAt: string;
  updatedAt: string;
}

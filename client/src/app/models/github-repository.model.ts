import { GithubRepositoryOwner } from "./github-repository-owner.model";

export class GithubRepository {
  constructor() {
  }

  id: number;
  name: string;
  fullName: string;
  owner: GithubRepositoryOwner;
  isPrivate: boolean;
  htmlUrl: string;
  description: string;
  isFork: boolean;
  url: string;
  languages: Map<string, string>;
  topics: Array<string>;
  createdAt: string;
  updatedAt: string;
  pushedAt: string;
  gitUrl;
  sshUrl;
  homepage: string;
  size: number;
  stargazersCount: number;
  watchersCount: number;
  language: string;
  hasIssues: boolean;
  hasDownloads: boolean;
  hasWiki: boolean;
  hasPages: boolean;
  forksCount: number;
  openIssuesCount: number;
  forks: number;
  openIssues: number;
  watchers: number;
  defaultBranch: string;
  networkCount: number;
  subscribersCount: number;
}

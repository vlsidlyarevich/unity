import {environment} from "../../environments/environment.dev-local";

export const config = {
  domain: environment.domain,
  api: environment.api,
  authApi: environment.authApi,
  userApi: 'http://localhost:8080/api/v1/user',
  userSocialApi: 'http://localhost:8080/api/v1/user/${userId}/social',
  analyticsApi: '/api/v1/user/{userId}/analytics',
  imageApi: 'http://localhost:8080/api/v1/image'
};

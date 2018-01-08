export const environment = {
  production: false,
  domain: 'http://localhost:8080',
  api: 'http://localhost:8080/api/v1',
  authApi: this.api + '/auth',
  userApi: this.api + '/user',
  userSocialApi: this.api + '/user/${userId}/social',
  imageApi: this.api + '/image'
};

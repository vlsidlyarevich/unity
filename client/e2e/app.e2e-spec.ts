import { UnityClientPage } from './app.po';

describe('unity-client App', function() {
  let page: UnityClientPage;

  beforeEach(() => {
    page = new UnityClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

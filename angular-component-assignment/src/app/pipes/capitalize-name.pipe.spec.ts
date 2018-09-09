import { CapitalizeNamePipe } from './capitalize-name.pipe';

describe('CapitalizeNamePipe', () => {
  it('create an instance', () => {
    const pipe = new CapitalizeNamePipe();
    expect(pipe).toBeTruthy();
  });
});

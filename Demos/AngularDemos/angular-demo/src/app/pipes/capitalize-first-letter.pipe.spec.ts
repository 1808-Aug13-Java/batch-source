import { CapitalizeFirstLetterPipe } from './capitalize-first-letter.pipe';

describe('CapitalizeFirstLetterPipe', () => {
  it('create an instance', () => {
    const pipe = new CapitalizeFirstLetterPipe();
    expect(pipe).toBeTruthy();
  });
});

import { CapitalizerPipe } from './capitalizer.pipe';

describe('CapitalizerPipe', () => {
  it('create an instance', () => {
    const pipe = new CapitalizerPipe();
    expect(pipe).toBeTruthy();
  });
});

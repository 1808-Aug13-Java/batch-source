import { FirstCapPipe } from './first-cap.pipe';

describe('FirstCapPipe', () => {
  it('create an instance', () => {
    const pipe = new FirstCapPipe();
    expect(pipe).toBeTruthy();
  });
});

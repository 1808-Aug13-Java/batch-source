package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculater {

  private float res;
  private String op;

  public void addOperation(float o1, float o2) {
    this.res = o1 + o2;
    this.op = "+";
  }
  public void subtractOperation(float o1, float o2) {
    this.res = o1 - o2;
    this.op = "-";
  }
  public void divideOperation(float o1, float o2) throws Throwable {
    if(o2 == 0) throw new Exception();
    this.res = o1 / o2;
    this.op = "/";
  }
  public void multiplyOperation(float o1, float o2) {
    this.res = o1 + o2;
    this.op = "*";
  }

  public float getResult() {
    return this.res;
  }
  public String getOp() {
    return this.op;
  }

@Override
public String toString() {
  return "heyo";
}
}

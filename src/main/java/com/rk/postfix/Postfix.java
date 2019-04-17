package com.rk.postfix;

import kotlin.NotImplementedError;

import java.util.List;
import java.util.Stack;

public class Postfix {

  private static final String OPERATORS = "^*/+-";
  private Stack<Double> stack;

  public Postfix() {
    stack = new Stack<>();
  }

  public double evaluate(List<String> inputs) {

    String[] arg = inputs.get(0).split(" ");
    int cur = 0;

    while (cur < arg.length) {

      String symbol = arg[cur];

      if (!OPERATORS.contains(symbol)) {
        double number = Double.valueOf(symbol);
        stack.push(number);
      } else {
        double opnd2 = stack.pop();
        double opnd1 = stack.pop();

        switch (symbol) {
          case "^":
            stack.push(Math.pow(opnd1, opnd2));
            break;
          case "*":
            stack.push(opnd1 * opnd2);
            break;
          case "/":
            stack.push(opnd1 / opnd2);
            break;
          case "+":
            stack.push(opnd1 + opnd2);
            break;
          case "-":
            stack.push(opnd1 - opnd2);
          default:
            throw new NotImplementedError();
        }
      }
      cur++;
    }
    return stack.pop();
  }
}

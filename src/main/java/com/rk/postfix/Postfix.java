package com.rk.postfix;

import java.util.Scanner;
import java.util.Stack;

public class Postfix {

  private static final String OPERATORS = "^*/+-";
  private Stack<Double> stack;

  public Postfix() {
    stack = new Stack<Double>();
  }

  public double evaluate(String postfix) {
    Scanner scan = new Scanner(postfix);

    while (scan.hasNext()) {
      String symbol = scan.next();

      if (!OPERATORS.contains(symbol)) {
        double number = Double.parseDouble(symbol);
        stack.push(number);
      } else {
        double opnd2 = stack.pop();
        double opnd1 = stack.pop();

        if (symbol.equals("^")) stack.push(Math.pow(opnd1, opnd2));
        else if (symbol.equals("*")) stack.push(opnd1 * opnd2);
        else if (symbol.equals("/")) stack.push(opnd1 / opnd2);
        else if (symbol.equals("+")) stack.push(opnd1 + opnd2);
        else stack.push(opnd1 - opnd2);
      }
    }
    return stack.pop();
  }
}

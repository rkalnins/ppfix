
import java.util.*;

public class Driver{
    public static void main(String[] args){
        PostfixEvaluator ppfix = new PostfixEvaluator();
//        Scanner in = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
//        String[]  inpu = in.nextLine().split(" ");
        for(String s: args){
            inputs.add(s);
        }
        System.out.println(ppfix.evaluate(inputs));
    }
}
class PostfixEvaluator {

    private Stack<Double> stack;
    private static final String OPERATORS = "^*/+-";

    public PostfixEvaluator()
    {
        stack = new Stack<Double>();
    }

    public double evaluate(ArrayList<String> inputs)
    {
        int cur = 0;
        while ( cur < inputs.size())
        {
            String symbol = inputs.get(cur);

            if (!OPERATORS.contains(symbol))
            {
                double number = Double.parseDouble(symbol);
                stack.push(number);
            }
            else
            {
                double opnd2 = stack.pop();
                double opnd1 = stack.pop();

                if (symbol.equals("^"))
                    stack.push(Math.pow(opnd1, opnd2));
                else if (symbol.equals("*"))
                    stack.push(opnd1 * opnd2);
                else if (symbol.equals("/"))
                    stack.push(opnd1 / opnd2);
                else if (symbol.equals("+"))
                    stack.push(opnd1 + opnd2);
                else
                    stack.push(opnd1 - opnd2);
            }
            cur++;
        }
        return stack.pop();
    }
}
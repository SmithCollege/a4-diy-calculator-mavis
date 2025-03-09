package a4;

import java.util.ArrayDeque;

/**
 * Represents Postfix class
 */
public class Postfix {

    /**
     * Given elements of a postfix expression, returns the result
     * @param tokens elements of a postfix expression
     * @return the result as a Double
     */
    public static Double postfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Double> stack = new ArrayDeque<>();
        
        for (Object token : tokens) {
            if (token instanceof Double) {
                stack.push((Double) token);
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Too few stack elements to perform: " + token);
                }
                Double num2 = stack.pop();
                Double num1 = stack.pop();
                String op = token.toString();
                Double result = 0.0;

                switch(op) {
                    case "+" :
                        result = num1 + num2;
                        break;
                    case "-" :
                        result = num1 - num2;
                        break;
                    case "*" :
                        result = num1 * num2;
                        break;
                    case "/" :
                        result = num1 / num2;
                        break;
                    case "^" :
                        result = Math.pow(num1, num2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operation: " + op);
                }

                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Incorrect number of remaining elements");
        }
        return stack.pop();
    }
}
package a4;

import java.util.ArrayDeque;

/**
 * Represents Infix class
 */
public class Infix {

    /**
     * Given elements of an infix expression, transforms it them postfix and returns the result
     * @param tokens elements of an infix expression
     * @return the result as a Double
     */
    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Object> stack = new ArrayDeque<>();
        ArrayDeque<Object> queue = new ArrayDeque<>();

        for (Object token : tokens) {
            if (token instanceof Double) {
                queue.add((Double) token);
            } else {
                String ch = token.toString();
                if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) {
                    System.out.println(ch);
                    while (!stack.isEmpty() && (precedence(stack.peek().toString().charAt(0)) > precedence(ch.charAt(0)) || (precedence(stack.peek().toString().charAt(0)) == precedence(ch.charAt(0))) && !ch.equals("^"))) {
                        queue.add(stack.pop());
                    }
                    stack.push(token);
                }  else if (ch.equals("(")) {
                    stack.push(token);
                } else if (ch.equals(")")) {
                    Boolean matchingParen = false;

                    while (!stack.isEmpty()) {
                        if (stack.peek().toString().equals("(")) {
                            matchingParen = true;
                            stack.pop();
                            break;
                        } else {
                            queue.add(stack.pop());
                        }
                    }
                    if (matchingParen == false) {
                        throw new IllegalArgumentException("Parenthesis mismatch");
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new IllegalArgumentException("Parenthesis mismatch");
            }
            queue.add(stack.pop());
        }

        return Postfix.postfix(queue);
    }

    /**
     * Returns an int representing an operator's ranking in the order of operations (PEMDAS)
     * @param operator an operator (^, *, /, +, -)
     * @return the operator's precedence
     */
    public static int precedence(char operator) {
        if (operator == '^') {
            return 3;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else {
            return -1;
        }
    }
}


package a4;

import java.util.ArrayDeque;

/**
 * Class for main method
 */
class Main {

  /**
   * Main method for running program
   * @param args empty array of Strings for storing command line arguments
   */
  public static void main(String[] args) {
    System.out.println("Calls from the command line:");
    System.out.println("    java Postfix <postfix-expr>");
    System.out.println("    java Calculate <infix-expr>");
    String expression = "65536^0.5^2";
    Tokenizer tokenizer = new Tokenizer();
    ArrayDeque<Object> tokens = new ArrayDeque<>();
    tokens = tokenizer.readTokens(expression);
    System.out.println(tokens);
    System.out.println(Infix.infixToPostfix(tokens));

  }
}
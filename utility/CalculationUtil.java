package utility;

import java.util.Stack;

public class CalculationUtil {

    public static String toPostfix(String infixExpression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new Stack<>();
        boolean isPreviousDigit = false;

        for (char symbol : infixExpression.toCharArray()) {

            if (Character.isDigit(symbol) || symbol == '.') {
                postfix.append(symbol);
                isPreviousDigit = true;
            } else if (symbol == '*' || symbol == '/' || symbol == '+' ||
                    symbol == '-' || symbol == '%' || symbol == '^') {
                if (isPreviousDigit) {
                    postfix.append(" ");
                    isPreviousDigit = false;
                }
                while (!operators.isEmpty() && getPriority(operators.peek()) >= getPriority(symbol) &&
                        operators.peek() != '(') {
                    postfix.append(operators.pop()).append(" ");
                }
                operators.push(symbol);
            } else if (symbol == '(') {
                operators.push(symbol);
            } else if (symbol == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(" ").append(operators.pop());
                }
                operators.pop();
            }
        }

        if(!operators.isEmpty()){
            postfix.append(" ");
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(" ");
        }

        return postfix.toString().trim();
    }
    public static double evaluateExpression(String postfixExpression) {
        String[] expressions = postfixExpression.split("\\s+");
        Stack<Double> operandStack = new Stack<>();

        for (String expression : expressions) {
            if (isNumeric(expression)) {
                operandStack.push(Double.parseDouble(expression));
            } else if (isOperator(expression)) {
                if (expression.equals("-") && operandStack.size() == 1) {

                    double operand = operandStack.pop();
                    operandStack.push(-operand);

                } else if (expression.equals("%")) {
                    double operand = operandStack.pop();
                    operandStack.push(operand / 100.0);
                } else {
                    double secondOperand = operandStack.pop();
                    double firstOperand = operandStack.pop();

                    switch (expression) {
                        case "*":
                            operandStack.push(firstOperand * secondOperand);
                            break;
                        case "/":
                            operandStack.push(firstOperand / secondOperand);
                            break;
                        case "+":
                            operandStack.push(firstOperand + secondOperand);
                            break;
                        case "-":
                            operandStack.push(firstOperand - secondOperand);
                            break;
                        case "^":
                            operandStack.push(Math.pow(firstOperand, secondOperand));
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported operator: " + expression);
                    }
                }
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Error while calculating an expression");
        }

        return operandStack.pop();
    }
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    private static boolean isOperator(String str) {
        return str.matches("[-+*/%^]");
    }
    private static int getPriority(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

}

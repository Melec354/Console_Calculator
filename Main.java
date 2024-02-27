import static utility.CalculationUtil.evaluateExpression;
import static utility.CalculationUtil.toPostfix;
import static utility.FormatUtil.toDecimalFormat;
import static utility.InputOutputUtil.getInfixExpression;
import static utility.InputOutputUtil.printMessage;


public class Main {
    public static void main(String[] args) {

        printMessage("calc");

        String infixExpression = getInfixExpression();

        try {

            String postfixExpression = toPostfix(infixExpression);
            printMessage("reverse polish notation is: ");
            printMessage(postfixExpression);

            double result = evaluateExpression(postfixExpression);
            printMessage("result: ");
            printMessage(toDecimalFormat(result));

        } catch (IllegalArgumentException exception) {
            printMessage("something gone wrong: " + exception.getMessage());
        }
    }
}

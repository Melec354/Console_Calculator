package utility;
import java.util.Scanner;

public class InputOutputUtil {
    public static String getInfixExpression(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter expression (and press enter):");
        String infixExpression;
        if(scanner.hasNextLine()){
            infixExpression = scanner.nextLine();
        } else {
            System.out.println("you made a mistake when entering an expression. Try again.");
            scanner.nextLine();
            infixExpression = getInfixExpression();
        }
        return infixExpression;
    }
    public static void printMessage(String message){
        System.out.println(message);
    }
}
package utility;
import java.text.DecimalFormat;
public class FormatUtil {
    public static String toDecimalFormat(double num){
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
        return decimalFormat.format(num);
    }
}

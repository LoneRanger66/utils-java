import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 保留两位小数
 */
public class FormatDecimal {    
    public static void decimalFormat(double number){
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(number));
    }

    public static void stringFormat(double number){
        System.out.println(String.format("%.2f",number));
    }

    public static void numberFormat(double number){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(number));
    }

    public static void bigDecimalFormat(double number){
        BigDecimal bg = new BigDecimal(number);
        System.out.println(bg.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }
    public static void main(String[] args) {
        double number = 3.144;
        decimalFormat(number);
        stringFormat(number);
        numberFormat(number);
        bigDecimalFormat(number);
    }
}

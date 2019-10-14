import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 格式化日期
 */
public class FormatDate {
    // 线程不安全
    public static void simpleDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }

    // 只适用于Java8及以上版本，线程安全
    public static void dateTimeFormatter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().format(dtf));
    }

    public static void main(String[] args) {
        simpleDateFormat();
        dateTimeFormatter();
    }
}

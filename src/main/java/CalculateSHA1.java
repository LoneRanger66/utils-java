import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 计算文件的SHA1值
 */
public class CalculateSHA1 {
    public static String calculateSHA1(String filename) throws Exception {
        try (FileInputStream in = new FileInputStream(new File(filename))) {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] buffer = new byte[100 * 1024 * 1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            byte[] result = messageDigest.digest();
            return BytesUtil.byteArrayToHexStringWithSpace(result);
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "D:\\Downloads\\xampp-windows-x64-7.3.2-0-VC15-installer.exe";
        String SHA1 = calculateSHA1(filename);
        System.out.println(SHA1);
    }
}

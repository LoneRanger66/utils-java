/**
 * 字节数组转String
 */
public class BytesUtil {
    public static String byteArrayToHexString(byte[] input) {
        char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder();
        for (byte b : input) {
            sb.append(table[(b >>> 4) & 0xF]);
            sb.append(table[(b & 0xF)]);
        }
        return sb.toString();
    }

    public static String byteArrayToHexStringWithSpace(byte[] input) {
        char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder();
        for (byte b : input) {
            sb.append(table[(b >>> 4) & 0xF]);
            sb.append(table[(b & 0xF)]);
            sb.append(" ");
        }
        return sb.toString();
    }
}

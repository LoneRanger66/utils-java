import java.util.ArrayList;
import java.util.List;

public class KMP {
    public void kmp(String text, String[] patterns) {
        List<Node> res = new ArrayList<>();
        for (String pattern : patterns) {
            res.addAll(match(text, pattern));
        }
        res.sort(null);
        int count = 1;
        int last = res.get(0).end;
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i).start > last) {
                count++;
                last = res.get(i).end;
            }
        }
        System.out.println(count);
    }

    class Node implements Comparable<Node> {
        int start;
        int end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Node o) {
            return this.end - o.end;
        }
    }

    /**
     * 将匹配的所有位置返回
     *
     * @param text    要匹配的字符
     * @param pattern 模式
     * @return 匹配到的所有位置
     */
    private List<Node> match(String text, String pattern) {
        List<Node> res = new ArrayList<>();
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    j = 0;
                    res.add(new Node(i - pattern.length(), i - 1));
                    i = i - pattern.length() + 1;
                }
            } else if (j != 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }
        return res;
    }

//    /**
//     * 返回pattern是否在text中
//     *
//     * @param text    要匹配的字符
//     * @param pattern 模式
//     * @return pattern在text中的位置，如果不在，返回-1
//     */
//    private int match(String text, String pattern) {
//        int[] next = getNext(pattern);
//        int i = 0, j = 0;
//        while (i < text.length() && j < pattern.length()) {
//            if (text.charAt(i) == pattern.charAt(j)) {
//                i++;
//                j++;
//            } else if (j != 0) {
//                j = next[j - 1];
//            } else {
//                i++;
//            }
//        }
//        return j == pattern.length() ? i - j : -1;
//    }

    /**
     * 得到next数组
     *
     * @param pattern 要匹配的pattern
     * @return next数组
     */
    private int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        int i = 1, j = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                next[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }
        return next;
    }
}
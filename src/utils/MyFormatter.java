package utils;

public class MyFormatter {
    /**
     * 在字符串前面补指定字符，使得总长度等于length，如果字符串长度大于指定值，直接返回输入的string
     *
     * @param string 要补字符的字符串
     * @param addChar 要补在前面的字符
     * @param length 补充之后的总长度
     * @return 补充之后的字符串
     */
    public static String addCharInFront(String string, char addChar, int length) {
        if (string.length() >= length) {
            return string;
        }
        StringBuilder resultBuilder = new StringBuilder(string);
        for (int i = 0; i < length - string.length(); i++) {
            resultBuilder.insert(0, addChar);
        }
        return resultBuilder.toString();
    }

    /**
     * 在字符串后面补指定字符，使得总长度等于length，如果字符串长度大于指定值，直接返回输入的string
     *
     * @param string 要补字符的字符串
     * @param addChar 要补在后面的字符
     * @param length 补充之后的总长度
     * @return 补充之后的字符串
     */
    public static String addCharInBehind(String string, char addChar, int length) {
        if (string.length() >= length) {
            return string;
        }
        StringBuilder resultBuilder = new StringBuilder(string);
        for (int i = 0; i < length - string.length(); i++) {
            resultBuilder.append(addChar);
        }
        return resultBuilder.toString();
    }
}

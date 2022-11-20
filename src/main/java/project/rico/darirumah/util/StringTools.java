package project.rico.darirumah.util;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringTools {

    /**
     * Tokenize and validate delimited string arrays is contain strTest
     *
     * @param strArray
     * @param strTest
     * @return
     */
    public static boolean isTokenizedStrContain(String strArray, String delimiter, String strTest) {
        if (StringTools.isEmptyOrNull(strTest))
            return false;

        return StringTools.getTokensWithCollection(strArray.toUpperCase(), delimiter).contains(strTest.toUpperCase());
    }

    /**
     * Check str is empty or null
     *
     * @param str a string to be test
     * @return boolean
     */
    public static boolean isEmptyOrNull(String str) {
        return (str == null || str.equals(""));
    }

    /**
     * Encapsulate as empty string if it was null
     *
     * @param str to be test
     * @return String
     */
    public static String emptyIfNull(String str) {
        if (isEmptyOrNull(str)) {
            return "";
        }
        return str;
    }

    /**
     * Tokenize String array with delimiter
     *
     * @param strArray
     * @param delimiter
     * @return List of String
     */
    public static List<String> getTokensWithCollection(String strArray, String delimiter) {
        return Collections.list(new StringTokenizer(strArray, delimiter)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    public static boolean equals(String str1, String str2) {
        if(isEmptyOrNull(str1) || isEmptyOrNull(str2)){
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }

}

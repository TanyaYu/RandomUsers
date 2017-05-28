package com.example.tanyayuferova.randomusers;

/**
 * Created by Tanya Yuferova on 5/28/2017.
 */

public class StringUtils {

    /**
     * Sets first symbol of input string to upper case
     * @param input input string
     * @return
     */
    public static String firstSymbolToUpperCase(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Sets first symbol to upper case for all words in input string
     * @param input
     * @return
     */
    public static String allWordsFirstSymbolsToUpperCase(String input) {
        String[] values = input.split(" ");
        String result = "";
        for(String value : values) {
            result += firstSymbolToUpperCase(value) + " ";
        }
        return result;
    }

}

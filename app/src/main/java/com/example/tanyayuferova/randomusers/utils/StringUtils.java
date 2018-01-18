package com.example.tanyayuferova.randomusers.utils;

import android.support.annotation.Nullable;

/**
 * Created by Tanya Yuferova on 5/28/2017.
 */

public final class StringUtils {

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

    /**
     * Concats string elements using delimiter
     * @param delimiter
     * @param elements
     * @return
     */
    @Nullable
    public static String join(CharSequence delimiter, CharSequence... elements) {
        if(delimiter == null)
            throw new NullPointerException("Delimiter must not be null");
        if(elements == null)
            throw new NullPointerException("Elements must not be null");

        StringBuilder builder = new StringBuilder();
        for (CharSequence cs: elements) {
            builder.append(delimiter).append(cs);
        }
        return builder.substring(delimiter.length());
    }

}

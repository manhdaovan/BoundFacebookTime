package com.manhdaovan.boundfacebooktime.utils;

/**
 * Created by manhdaovan on 10/28/17.
 */

public class Utils {
    private static final char DOUBLE_QUOTE = '"';

    public static String attributeToJson(String attribute, Object value){
        return DOUBLE_QUOTE + attribute + DOUBLE_QUOTE + ":" + DOUBLE_QUOTE + value + DOUBLE_QUOTE;
    }
}

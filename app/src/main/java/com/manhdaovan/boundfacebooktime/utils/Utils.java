package com.manhdaovan.boundfacebooktime.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by manhdaovan on 10/28/17.
 */

public class Utils {
    private static final char DOUBLE_QUOTE = '"';

    public static String attributeToJson(String attribute, Object value) {
        return DOUBLE_QUOTE + attribute + DOUBLE_QUOTE + ":" + DOUBLE_QUOTE + value + DOUBLE_QUOTE;
    }

    public static void alertLongTime(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void alertShortTime(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void setTextViewError(TextView tv) {
        tv.setTextColor(Color.parseColor(Constants.RED_IN_HEX));
    }

    public static void setTextViewNormal(TextView tv) {
        tv.setTextColor(Color.parseColor(Constants.GRAY_IN_HEX));
    }
}

package ru.hostco.burovalex.webapptest;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static boolean logSwitch = true;
    static List<String> dayOfWeek = new ArrayList<String>();
    static {
        dayOfWeek.add("Пн");
        dayOfWeek.add("Вт");
        dayOfWeek.add("Ср");
        dayOfWeek.add("Чт");
        dayOfWeek.add("Пт");
        dayOfWeek.add("Сб");
        dayOfWeek.add("Вс");
    }

    public static List<String> getDayOfWeekList() {
        return dayOfWeek;
    }


    public static void log(String s) {
        if (logSwitch) System.out.println(s);
    }

    public static void logError(String s) {
        if (logSwitch) System.err.println(s);
    }

    public static void logError(Exception e) {
        if (logSwitch) System.err.println(e.getMessage());
    }
}

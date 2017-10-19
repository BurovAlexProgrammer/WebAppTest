package ru.hostco.burovalex.webapptest.services;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;

public class Common {
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
}

package ru.hostco.burovalex.webapptest;

import ru.hostco.burovalex.webapptest.services.Common;

import static ru.hostco.burovalex.webapptest.services.Common.logSwitch;

public class Procedure  {
    public Procedure(String name, String doctorFullName, int procedurePrice, int procedureDay, int roomNumber, int proceduteTime) {
        this.name = name;
        this.doctorFullName = doctorFullName;
        this.procedurePrice = procedurePrice;
        this.procedureDay = procedureDay;
        this.roomNumber = roomNumber;
        this.proceduteTime = proceduteTime;
    }

    public String name, doctorFullName;
    public int procedurePrice = -1, procedureDay = -1, roomNumber;
    public long proceduteTime = -1;

    public String getProcedureName() {
        return name;
    }

    public static String[] getNames(Procedure[] procedures) {
        log("[getNames]");
        String[] result = new String[procedures.length];
        log("count: "+procedures.length);
        for (int i=0; i<procedures.length;i++) {
            log(""+i);
            log("name["+i+"]: "+procedures[i].getProcedureName());
            result[i] = procedures[i].getProcedureName();
        }
        return result;
    }


    static void log(String s) {
        if (logSwitch) System.out.println(s);
    }

    static void logError(String s) {
        if (logSwitch) System.err.println(s);
    }

    static void logError(Exception e) {
        if (logSwitch) System.err.println(e.getMessage());
    }
}

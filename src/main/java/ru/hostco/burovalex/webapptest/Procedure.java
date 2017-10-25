package ru.hostco.burovalex.webapptest;

import ru.hostco.burovalex.webapptest.services.Common;

import static ru.hostco.burovalex.webapptest.services.Common.logSwitch;

public class Procedure  {
    public Procedure(int procedureId, String procedureName, String doctorFullName, int procedurePrice, int procedureDay, int procedureTime, int roomNumber) {
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.doctorFullName = doctorFullName;
        this.procedurePrice = procedurePrice;
        this.procedureDay = procedureDay;
        this.roomNumber = roomNumber;
        this.procedureTime = procedureTime;
    }

    String procedureName, doctorFullName;

    int procedureId, procedurePrice = -1, procedureDay = -1, procedureTime = -1, roomNumber;

    public int getProcedureId() {
        return procedureId;
    }
    public String getProcedureName() {
        return procedureName;
    }
    public String getDoctorFullName() {
        return doctorFullName;
    }
    public int getProcedurePrice() {
        return procedurePrice;
    }
    public int getProcedureDay() {
        return procedureDay;
    }
    public int getProcedureTime() {
        return procedureTime;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public static int[] getIds(Procedure[] procedures) {
        int[] result = new int[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedureId();
        }
        return result;
    }
    public static String[] getNames(Procedure[] procedures) {
        String[] result = new String[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedureName();
        }
        return result;
    }
    public static String[] getDoctorNames(Procedure[] procedures) {
        String[] result = new String[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getDoctorFullName();
        }
        return result;
    }
    public static int[] getPrices(Procedure[] procedures) {
        int[] result = new int[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedurePrice();
        }
        return result;
    }
    public static int[] getProcedureDays(Procedure[] procedures) {
        int[] result = new int[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedureDay();
        }
        return result;
    }
    public static int[] getProcedureTimes(Procedure[] procedures) {
        int[] result = new int[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedureTime();
        }
        return result;
    }
    public static int[] getRoomNumbers(Procedure[] procedures) {
        int[] result = new int[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getRoomNumber();
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

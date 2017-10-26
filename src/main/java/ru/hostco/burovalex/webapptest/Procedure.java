package ru.hostco.burovalex.webapptest;

import ru.hostco.burovalex.webapptest.services.Common;


import java.util.Date;

import static ru.hostco.burovalex.webapptest.services.Common.logSwitch;

public class Procedure  {
    public Procedure() {
        procedureId=-1;
        procedureName="Noname";
        doctorFullName="Noname";
        procedurePrice=0;
        procedureDay=0;
        procedureTime=new Date();
        roomNumber=0;
    }
    public Procedure(int procedureId, String procedureName, String doctorFullName, int procedurePrice, int procedureDay, Date procedureTime, int roomNumber) {
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.doctorFullName = doctorFullName;
        this.procedurePrice = procedurePrice;
        this.procedureDay = procedureDay;
        this.roomNumber = roomNumber;
        this.procedureTime = procedureTime;
    }

    String procedureName, doctorFullName;

    int procedureId;

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public void setProcedurePrice(int procedurePrice) {
        this.procedurePrice = procedurePrice;
    }

    public void setProcedureDay(int procedureDay) {
        this.procedureDay = procedureDay;
    }

    public void setProcedureTime(Date procedureTime) {
        this.procedureTime = procedureTime;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    int procedurePrice = -1;
    int procedureDay = -1;
    Date procedureTime = new Date();
    int roomNumber;

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
    public Date getProcedureTime() {
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
    public static long[] getProcedureTimes(Procedure[] procedures) {
        long[] result = new long[procedures.length];
        for (int i=0; i<procedures.length;i++) {
            result[i] = procedures[i].getProcedureTime().getTime();
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
}

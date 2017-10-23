package ru.hostco.burovalex.webapptest;

public class Procedure  {
    private String name, doctorFullName;
    private int procedurePrice = -1, procedureDay = -1, roomNumber;
    private long proceduteTime = -1;

    public String getProcedureName() {
        return name;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public long getProceduteTime() {
        return proceduteTime;
    }

    public int getProcedurePrice() {
        return procedurePrice;
    }

    public int getProcedureDay() {
        return procedureDay;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setProcedureName(String procedureName) {
        this.name = procedureName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public void setProceduteTime(long proceduteTime) {
        this.proceduteTime = proceduteTime;
    }

    public void setProcedurePrice(int procedurePrice) {
        this.procedurePrice = procedurePrice;
    }

    public void setProcedureDay(int procedureDay) {
        this.procedureDay = procedureDay;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}

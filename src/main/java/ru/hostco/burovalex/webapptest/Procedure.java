package ru.hostco.burovalex.webapptest;

public class Procedure  {
    private String procedureName, doctorFullName, proceduteTime;
    private int procedurePrice = -1, procedureDay = -1, roomNumber;

    public String getProcedureName() {
        return procedureName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public String getProceduteTime() {
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
        this.procedureName = procedureName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public void setProceduteTime(String proceduteTime) {
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

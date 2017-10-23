package ru.hostco.burovalex.webapptest;

import org.exolab.castor.types.DateTime;

import java.sql.*;
import java.util.Date;

public class MySQL {

        public Connection connection;
        public Statement statement;
        public ResultSet resultSet;

    final static String path="jdbc:sqlite:procedures.db";
    static class procedure {
        static class field{final static String
                id = "id",
                name = "name",
                doctorFullName = "doctorName",
                price = "price",
                day = "dayOfWeek",
                time = "time",
                roomNumber = "roomNumber";
        }
        class type{final static String
                id = "INTEGER PRIMARY KEY AUTOINCREMENT",
                name = "text",
                doctorFullName = "text",
                price = "int",
                day = "int",
                time = "int",
                roomNumber = "int";}
        class table {final static String name="procedure";}
    }


        // --------Создание таблицы--------
        public void createDB() throws ClassNotFoundException, SQLException
        {
            try {

                String space = " ";
                Class.forName("org.sqlite.JDBC");
                String sqlQuery = "CREATE TABLE if not exists "+ procedure.table.name+" (" +
                        withQuotes(procedure.field.id) +space+ procedure.type.id+", "+
                        withQuotes(procedure.field.name) +space+ procedure.type.name+", "+
                        withQuotes(procedure.field.doctorFullName) +space+ procedure.type.doctorFullName+", "+
                        withQuotes(procedure.field.price) +space+ procedure.type.price+", "+
                        withQuotes(procedure.field.day) +space+ procedure.type.day+", "+
                        withQuotes(procedure.field.time) +space+ procedure.type.time+", "+
                        withQuotes(procedure.field.roomNumber) +space+ procedure.type.roomNumber+");";
                statement.execute(sqlQuery);
                System.out.println("Таблица создана или уже существует.");
            } catch (SQLException e) {logError(e.getMessage());}
        }

        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        public void Connect() throws ClassNotFoundException, SQLException
        {
            try {
                connection = null;
                statement = null;
                connection = DriverManager.getConnection(path);
                System.out.println("База Подключена!");
                statement = connection.createStatement();
        } catch (SQLException e) {System.err.println(e.getMessage());}
        }

        // --------Заполнение таблицы--------
        public void WriteProcedure(String name, String doctorFullName, int price, int day, int time, int roomNumber)
        {
            try {
                String sqlQuery = ("INSERT INTO "+ procedure.table.name +" ("+
                        procedure.field.name + ", " + procedure.field.doctorFullName + ", " + procedure.field.price + ", " + procedure.field.day + ", " + procedure.field.time + ", " + procedure.field.roomNumber +
                        ") VALUES (" +
                        withQuotes(name) + " ," + withQuotes(doctorFullName) + " ," + price + " ," + day + " ," + time + " ," + roomNumber +
                        ");");
                //sqlQuery = "INSERT INTO procedure (name, doctorName, price) VALUES ('name123', 'Doc', 1000)";
                log(sqlQuery);
                statement.execute(sqlQuery);
                log("Таблица заполнена");
            } catch (SQLException e) {logError(e);}
        }

        // -------- Вывод таблицы--------
        public void ReadProcedure() throws ClassNotFoundException, SQLException
        {
            resultSet = statement.executeQuery("SELECT * FROM procedure");
            while(resultSet.next())
            {
                int id = resultSet.getInt(procedure.field.id);
                String  name = resultSet.getString(procedure.field.name);
                String doctorName = resultSet.getString(procedure.field.doctorFullName);
                int price = resultSet.getInt(procedure.field.price);
                int day = resultSet.getInt(procedure.field.day);
                int time = resultSet.getInt(procedure.field.time);
                int roomNumber = resultSet.getInt(procedure.field.roomNumber);

                log( "ID = " + id );
                log( "name = " + name );
                log( "doctorName = " + doctorName );
                log( "price = " + price );
                log("day = "+day);
                log("time = "+time);
                log("roomNumber = "+roomNumber);
            }
            log("Таблица выведена");
        }

        // --------Закрытие--------
        public void CloseDB() throws ClassNotFoundException, SQLException
        {
            try {
                connection.close();
                statement.close();
                resultSet.close();
                System.out.println("Соединение с БД закрыто.");
            } catch (SQLException e) {logError(e);}
        }

        public Procedure[] getProcedures() {
            Procedure[] procedures = null;
            try {
                resultSet = statement.executeQuery("SELECT * FROM procedure");
                procedures = new Procedure[getRowCount(resultSet)];
                int i=0;
                while (resultSet.next()) {
                    int id = resultSet.getInt(procedure.field.id);
                    String name = resultSet.getString(procedure.field.name);
                    String doctorName = resultSet.getString(procedure.field.doctorFullName);
                    int price = resultSet.getInt(procedure.field.price);
                    int day = resultSet.getInt(procedure.field.day);
                    int time = resultSet.getInt(procedure.field.time);
                    int roomNumber = resultSet.getInt(procedure.field.roomNumber);
                    procedures[i].setProcedureName(name);
                    procedures[i].setDoctorFullName(doctorName);
                    procedures[i].setProcedurePrice(price);
                    procedures[i].setProcedureDay(day);
                    procedures[i].setProceduteTime(time);
                    procedures[i].setRoomNumber(roomNumber);
                }
                log("Таблица выведена");
            } catch (SQLException e) {logError(e);}
            return procedures;
        }

    void log(String s) {
        System.out.println(s);
    }

    void logError(String s) {
            System.err.println(s);
    }

    void logError(Exception e) {
            System.err.println(e.getMessage());
    }

    String withQuotes(String s) {return "'"+s+"'";}

    int getRowCount(ResultSet resultSet) {
        int i = 0;
        try {
            while (resultSet.next()) {
                i++;
            }
        } catch (SQLException e) {logError(e);}
        return i;
    }
}

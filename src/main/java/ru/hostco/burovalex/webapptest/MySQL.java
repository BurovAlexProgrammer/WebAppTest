package ru.hostco.burovalex.webapptest;

import org.exolab.castor.types.DateTime;

import java.sql.*;
import java.util.Date;

public class MySQL {

        public Connection connection;
        public Statement statement;
        public ResultSet resultSet;
        final String path = "jdbc:sqlite:test.db";


        // --------Создание таблицы--------
        public void createDB() throws ClassNotFoundException, SQLException
        {
            try {
                statement = null;
                Class.forName("org.sqlite.JDBC");
                String sqlQuery = "CREATE TABLE if not exists table1 ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, 'NAME' text);";
                statement = connection.createStatement();
                statement.execute(sqlQuery);
                System.out.println("Таблица создана или уже существует.");
            } catch (SQLException e) {logError(e.getMessage());}
        }

        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        public void Connect() throws ClassNotFoundException, SQLException
        {
            try {
                connection = null;
                connection = DriverManager.getConnection(path);
                System.out.println("База Подключена!");
        } catch (SQLException e) {System.err.println(e.getMessage());}
        }

        // --------Заполнение таблицы--------
        public void WriteProcedure() throws SQLException
        {
            try {
                String sqlQuery = "INSERT INTO table1 (name) VALUES ('name123')";
                log(sqlQuery);

//                statement.execute("INSERT INTO " + procedure.table.name + " (" +
//                        procedure.field.name + ", " + procedure.field.doctorFullName + ", " + procedure.field.price + ", " + procedure.field.day + ", " + procedure.field.time + ", " + procedure.field.roomNumber +
//                        ") " +
//                        "VALUES (" +
//                        name + " ," + doctorFullName + " ," + price + " ," + day + " ," + time + " ," + roomNumber +
//                        "); ");
                statement.execute(sqlQuery);
                log("Таблица заполнена");
            } catch (SQLException e) {logError(e);}
        }

        // -------- Вывод таблицы--------
        public void ReadProcedure() throws ClassNotFoundException, SQLException
        {
            resultSet = statement.executeQuery("SELECT * FROM table1");
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String  name = resultSet.getString("name");
                System.out.println( "ID = " + id );
                System.out.println( "name = " + name );
            }
            System.out.println("Таблица выведена");
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

    void log(String s) {
        System.out.println(s);
    }

    void logError(String s) {
            System.err.println(s);
    }

    void logError(Exception e) {
            System.err.println(e.getMessage());
    }
}

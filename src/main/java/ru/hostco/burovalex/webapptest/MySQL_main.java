package ru.hostco.burovalex.webapptest;

import java.sql.*;

public class MySQL_main {

    public void test() throws ClassNotFoundException, SQLException {
        Connect();
        deleteDB();
        createDB();
        //db.WriteDB();
        ReadProcedure();
        CloseDB();
    }



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
                    name = "TEXT",
                    doctorFullName = "TEXT",
                    price = "INT",
                    day = "INT",
                    time = "INT",
                    roomNumber = "INT";}
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
                statement = connection.createStatement();
//                statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
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
        public void WriteProcedure(String name, String doctorFullName, int price, int day, long time, int roomNumber) throws SQLException
        {
            try {
                String sqlQuery = "INSERT INTO " + procedure.table.name+ " (" +
                        "name" +
                        ") " +
                        "VALUES (" +
                        "'123'"+
                        ");";
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
            resultSet = statement.executeQuery("SELECT * FROM "+ procedure.table.name);
            while(resultSet.next())
            {
//                int id = resultSet.getInt(procedure.field.id);
//                String  name = resultSet.getString(procedure.field.name);
//                String  doctorFullName = resultSet.getString(procedure.field.doctorFullName);
//                int price = resultSet.getInt(procedure.field.price);
//                int day = resultSet.getInt(procedure.field.day);
//                long time = resultSet.getLong(procedure.field.time);
//                int roomNumber = resultSet.getInt(procedure.field.roomNumber);
                int id = resultSet.getInt("id");
                String  name = resultSet.getString("name");
//                String  doctorFullName = resultSet.getString(procedure.field.doctorFullName);
//                int price = resultSet.getInt(procedure.field.price);
//                int day = resultSet.getInt(procedure.field.day);
//                long time = resultSet.getLong(procedure.field.time);
//                int roomNumber = resultSet.getInt(procedure.field.roomNumber);
                System.out.println( "ID = " + id );
                System.out.println( "name = " + name );
//                System.out.println( "doctorName = " + doctorFullName );
//                System.out.println( "price = " + price );
//                System.out.println( "day = " + day );
//                System.out.println( "time = " + time);
//                System.out.println( "roomNumber = " + roomNumber);
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

        public void deleteDB() {
//            try {
//                String space = " ";
//                Class.forName("org.sqlite.JDBC");
//                String sqlQuery = "CREATE TABLE if not exists "+ procedure.table.name+" (" +
//                        withQuotes(procedure.field.id) +space+ procedure.type.id+", "+
//                        withQuotes(procedure.field.name) +space+ procedure.type.name+", "+
//                        withQuotes(procedure.field.doctorFullName) +space+ procedure.type.doctorFullName+", "+
//                        withQuotes(procedure.field.price) +space+ procedure.type.price+", "+
//                        withQuotes(procedure.field.day) +space+ procedure.type.day+", "+
//                        withQuotes(procedure.field.time) +space+ procedure.type.time+", "+
//                        withQuotes(procedure.field.roomNumber) +space+ procedure.type.roomNumber+");";
//                statement = connection.createStatement();
////                statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
//                statement.execute(sqlQuery);
//                System.out.println("Таблица создана или уже существует.");
//            } catch (SQLException e) {logError(e.getMessage());}
        }

    String withQuotes(String s) {return "'"+s+"'";}

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

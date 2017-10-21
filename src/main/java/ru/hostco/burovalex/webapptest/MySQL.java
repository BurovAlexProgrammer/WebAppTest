package ru.hostco.burovalex.webapptest;

import java.sql.*;

public class MySQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        db.Connect();
        db.CreateDB();
        //db.WriteDB();
        db.ReadDB();
        db.CloseDB();
    }


    public static class db {
        public static Connection connection;
        public static Statement statement;
        public static ResultSet resultSet;

        // --------Создание таблицы--------
        public static void CreateDB() throws ClassNotFoundException, SQLException
        {
            try {
                Class.forName("org.sqlite.JDBC");
                statement = connection.createStatement();
                statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
                System.out.println("Таблица создана или уже существует.");
            } catch (SQLException e) {System.err.println(e.getMessage());}
        }

        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        public static void Connect() throws ClassNotFoundException, SQLException
        {
            try {
                connection = null;
                connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                System.out.println("База Подключена!");
        } catch (SQLException e) {System.err.println(e.getMessage());}
        }

        // --------Заполнение таблицы--------
        public static void WriteDB() throws SQLException
        {
            statement.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
            statement.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
            statement.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

            System.out.println("Таблица заполнена");
        }

        // -------- Вывод таблицы--------
        public static void ReadDB() throws ClassNotFoundException, SQLException
        {
            resultSet = statement.executeQuery("SELECT * FROM users");

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String  name = resultSet.getString("name");
                String  phone = resultSet.getString("phone");
                System.out.println( "ID = " + id );
                System.out.println( "name = " + name );
                System.out.println( "phone = " + phone );
                System.out.println();
            }

            System.out.println("Таблица выведена");
        }

        // --------Закрытие--------
        public static void CloseDB() throws ClassNotFoundException, SQLException
        {
            try {
                connection.close();
                statement.close();
                resultSet.close();
                System.out.println("Соединение с БД закрыто.");
            } catch (SQLException e) {}
        }


    }
}

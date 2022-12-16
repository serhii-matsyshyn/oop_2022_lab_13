package task2;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection dbConnection = null;

    private Connection connection;

    @SneakyThrows
    private DBConnection() {
        connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        // check if connection is not open successfully
        if (connection == null) {
            throw new Exception("Connection is null");
        }
        // create table user
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS website (link TEXT PRIMARY KEY, text TEXT)");

        System.out.println("Opened database successfully");
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    @SneakyThrows
    public void executeQuery(String query) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    @SneakyThrows
    public String executeQueryWithAnswer(String query){
        Statement stmt = connection.createStatement();
        String result = stmt.executeQuery(query).getString(1);
        stmt.close();
        return result;
    }
}
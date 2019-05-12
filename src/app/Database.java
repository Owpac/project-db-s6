package app;

import java.sql.*;
import java.sql.Statement;

public class Database {

    private Connection connection;

    /**
     * Class that create the connection to our database.
     */
    public Database() {

        String url = "jdbc:mysql://den1.mysql3.gear.host:3306/projectdbs6";
        String user = "projectdbs6";
        String password = Password.PASSWORD;

        try {
            /*Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");*/
            this.connection = DriverManager.getConnection( url, user, password );
            System.out.println( "Database connected !" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Execute a query.
     *
     * @param query Query to execute in the database.
     */
    public void execute(String query) {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute( query );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
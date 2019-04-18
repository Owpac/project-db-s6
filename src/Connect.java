import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public Connect() {

        String url = "jdbc:mysql://den1.mysql3.gear.host:3306/projectdbs6";
        String user = "projectdbs6";
        String password = "password!";

        try {

            /*Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");*/

            Connection connection = DriverManager.getConnection( url, user, password );
            System.out.println( "Database connected !" );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}